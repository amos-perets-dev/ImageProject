package com.example.imageproject.network.base;

import retrofit2.Retrofit;

public interface IBaseNetworkManager {

    /**
     * Create the retrofit to handel the request / response
     * @return
     */
    Retrofit buildRetrofit();

}
