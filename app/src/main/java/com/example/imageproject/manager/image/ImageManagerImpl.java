package com.example.imageproject.manager.image;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.imageproject.data.Result;
import com.example.imageproject.image_app.base.manager.BaseManager;
import com.example.imageproject.network.api.ImageApi;
import com.example.imageproject.network.error.IHandleNetworkError;
import com.example.imageproject.repo.ImageRepo;
import com.example.imageproject.utils.files.IFilesUtil;
import com.example.imageproject.utils.image.IImageUtil;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ImageManagerImpl extends BaseManager implements IImageManager {

    private final List<String> imagesUrls = Arrays.asList("125%402.zip", "127%402.zip");

    private final IImageUtil imageUtil;
    private final IFilesUtil filesUtil;
    private final ImageRepo imageRepo;
    private final ImageApi imageApi;

    private final BehaviorSubject<Result> state = BehaviorSubject.create();

    @Inject
    public ImageManagerImpl(ImageApi imageApi,
                            ImageRepo imageRepo,
                            IImageUtil imageUtil,
                            IFilesUtil filesUtil) {

        this.imageApi = imageApi;
        this.imageRepo = imageRepo;
        this.filesUtil = filesUtil;
        this.imageUtil = imageUtil;
        load();
    }

    @Override
    public void load() {
        Integer index = this.imageRepo.getIndex();
        Log.d("TEST_GAME", "ImageManagerImpl index: " + index);
        loadImage(imagesUrls.get(index), imageApi);
    }

    public void loadImage(String suffixUrl, ImageApi imageApi) {
        Log.d("TEST_GAME", "ImageManagerImpl load: " + suffixUrl);
        compositeDisposable.add(
                imageApi
                        .getImage(suffixUrl)
                        .subscribeOn(Schedulers.io())
                        .map(Response::body)
                        .map(ResponseBody::byteStream)
                        .map(filesUtil::unzip)
                        .map(imageUtil::createBitmap)
                        .doOnNext(this::addImage)
                        .flatMap(bitmap -> imageRepo
                                .getImage()
                                .map(Result.Success::new)
                                .doOnNext(state::onNext))
                        .subscribe(ignored -> {
                        }, this::notifyError)
        );

    }

    private void notifyError(Throwable throwable) {
        state.onNext(new Result.Error(throwable));
    }

    /**
     * Add image to the {@link ImageRepo}
     */
    private void addImage(Bitmap bitmap) {
        this.imageRepo
                .addImage(bitmap, imagesUrls.size());
    }

    @Override
    public Observable<Result> getState() {
        return state.hide()
                .subscribeOn(Schedulers.io());
    }

}
