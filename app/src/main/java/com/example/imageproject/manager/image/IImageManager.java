package com.example.imageproject.manager.image;

import com.example.imageproject.data.Result;
import com.example.imageproject.image_app.base.manager.IBaseManager;

import io.reactivex.Observable;

public interface IImageManager extends IBaseManager {

    /**
     * Get the load state
     *
     * @return {@link Observable<Result>} curr state
     */
    Observable<Result> getState();

    /**
     * Start the load data
     */
    void load();
}
