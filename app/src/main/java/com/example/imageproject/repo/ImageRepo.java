package com.example.imageproject.repo;

import android.graphics.Bitmap;

import io.reactivex.Observable;

public interface ImageRepo {

    /**
     * Add the image from server
     *
     * @param bitmap curr image
     * @param sizeLimit images list size
     */
    void addImage(Bitmap bitmap, int sizeLimit);

    /**
     * Get the image
     *
     * @return {@link Observable<Bitmap>}
     */
    Observable<Bitmap> getImage();

    /**
     * Get the curr image index
     *
     * @return curr index
     */
    Integer getIndex();
}
