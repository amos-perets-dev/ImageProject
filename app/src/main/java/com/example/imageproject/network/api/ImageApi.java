package com.example.imageproject.network.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ImageApi {

    /**
     * Get the image when the user open the APP and need to load image
     *
     * @param filepath url
     * @return {@link Observable<Response<ResponseBody>>} handle the data image
     */
    @GET
    @Streaming
    Observable<Response<ResponseBody>> getImage(@Url String filepath);

}
