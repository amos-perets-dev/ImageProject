package com.example.imageproject.screens.home_page;

import androidx.lifecycle.MutableLiveData;

import com.example.imageproject.data.Result;
import com.example.imageproject.image_app.base.ViewModelBase;
import com.example.imageproject.manager.image.IImageManager;
import com.example.imageproject.network.error.IHandleNetworkError;
import com.example.imageproject.network.helper.INetworkConnectivityHelper;

import javax.inject.Inject;

import io.reactivex.internal.functions.Functions;

public class HomeViewModel extends ViewModelBase {

    final MutableLiveData<Result> imageNotifier = new MutableLiveData<>();

    private final IImageManager imageManager;
    private final INetworkConnectivityHelper networkConnectivityHelper;

    @Inject
    public HomeViewModel(IImageManager imageManager,
                         IHandleNetworkError handleNetworkError,
                         INetworkConnectivityHelper networkConnectivityHelper) {
        super(handleNetworkError);
        imageNotifier.postValue(new Result.Start());
        this.imageManager = imageManager;
        this.networkConnectivityHelper = networkConnectivityHelper;
        compositeDisposable.add(
                imageManager
                        .getState()
                        .doOnNext(imageNotifier::postValue)
                        .filter(state -> state instanceof Result.Error)
                        .cast(Result.Error.class)
                        .map(Result.Error::getThrowable)
                        .doOnNext(this::notifyError)
                        .subscribe(ignored -> {
                        }, this::notifyError)
        );

        compositeDisposable.add(
                networkConnectivityHelper
                        .getNetworkChanges()
                        .filter(Functions.equalsWith(true))
                        .flatMap(networkState -> imageManager
                                .getState()
                                .filter(state -> state instanceof Result.Error)
                                .doOnNext(this::startLoad)
                        )
                        .subscribe()
        );
    }

    /**
     * Start the load data state
     */
    private void startLoad(Result ignored) {
        notifyState(new Result.Start());
        imageManager.load();
    }

    /**
     * Notify the new state
     *
     * @param result {@link Result}
     */
    private void notifyState(Result result) {
        imageNotifier.postValue(result);
    }

    @Override
    protected void onCleared() {
        this.imageManager.dispose();
        this.networkConnectivityHelper.dispose();
        super.onCleared();
    }
}
