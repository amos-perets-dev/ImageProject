package com.example.imageproject.repo;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.imageproject.manager.sharedpreferences.IPreferencesManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ImageRepoImpl implements ImageRepo{

    private final BehaviorSubject<Bitmap> image = BehaviorSubject.create();

    private final IPreferencesManager preferencesManager;

    private int imageIndex;

    @Inject
    public ImageRepoImpl(IPreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    private void addIndex(int sizeLimit) {
        int currIndex = ++imageIndex % sizeLimit;
        preferencesManager.addIndex(currIndex);
    }

    @Override
    public Integer getIndex() {
        imageIndex = preferencesManager.getIndex();
        return imageIndex;
    }

    @Override
    public void addImage(Bitmap bitmap, int sizeLimit) {
        image.onNext(bitmap);
        addIndex(sizeLimit);
    }

    @Override
    public Observable<Bitmap> getImage() {
        return image.hide();
    }
}
