package com.example.imageproject.network.error;

import org.jetbrains.annotations.NotNull;

public interface IHandleNetworkError {

    /**
     * Generate error id {@link androidx.annotation.StringRes}
     * @param throwable curr {@link Throwable}
     * @return {@link androidx.annotation.StringRes}
     */
    int generateErrorID(@NotNull Throwable throwable);

}
