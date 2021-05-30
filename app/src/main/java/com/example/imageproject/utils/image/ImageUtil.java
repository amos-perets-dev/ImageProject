package com.example.imageproject.utils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.inject.Inject;

public class ImageUtil implements IImageUtil {

    @Inject
    public ImageUtil() {

    }

    @Override
    public Bitmap createBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
