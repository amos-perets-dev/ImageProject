package com.example.imageproject.network.error;

import androidx.annotation.StringRes;

import com.example.imageproject.R;

import org.jetbrains.annotations.NotNull;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;


public class HandleNetworkError implements IHandleNetworkError {

    @Inject
    public HandleNetworkError() {
    }

    @StringRes
    public int generateErrorID(@NotNull Throwable it) {

        return (it instanceof UnknownHostException)
                || (it instanceof SSLHandshakeException)
                || (it instanceof TimeoutException)
                || (it instanceof ConnectException)
                || (it instanceof SSLProtocolException) ? R.string.internet_error_text : R.string.general_error_text;
    }
}
