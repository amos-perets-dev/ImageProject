package com.example.imageproject.image_app.base.manager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BaseManager implements IBaseManager {

    @Inject
    public BaseManager() {
    }

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }


}
