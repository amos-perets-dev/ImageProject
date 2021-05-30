package com.example.imageproject.utils.image;

import android.graphics.Bitmap;

public interface IImageUtil {

    /**
     * Create a {@link Bitmap}
     *
     * @param bytes need to convert to {@link Bitmap}
     * @return {@link Bitmap}
     */
    Bitmap createBitmap(byte[] bytes);
}
