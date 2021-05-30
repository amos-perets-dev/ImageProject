package com.example.imageproject.data;

import android.graphics.Bitmap;

import com.example.imageproject.R;

/**
 * A simple class that handle the data state
 */
public class Result {

    public static class Success extends Result {
        private Bitmap image;

        public Success(Bitmap image) {
            this.image = image;

        }

        public Bitmap getImage() {
            return image;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }
    }

    public static class Error extends Result {
        private final Throwable throwable;

        public Error(Throwable throwable) {
            this.throwable = throwable;
        }

        public int getImage() {
            return R.drawable.ic_error;
        }

        public Throwable getThrowable() {
            return throwable;
        }

    }

    public static class Start extends Result {
    }
}
