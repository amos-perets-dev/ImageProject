package com.example.imageproject.network.base;

import com.example.imageproject.BuildConfig;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseNetworkManagerImpl implements IBaseNetworkManager {

    private Retrofit retrofit = null;

    @Override
    public Retrofit buildRetrofit() {
        if (this.retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(120L, TimeUnit.SECONDS)
                    .readTimeout(120L, TimeUnit.SECONDS);
            httpClient.addInterceptor(chain -> {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Request request_ = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("charset", "UTF-8")
                        .build();
                return chain.proceed(request_);
            });
            this.retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).setLenient().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return this.retrofit;
    }

}
