package com.example.imageproject.network.helper;

import com.example.imageproject.image_app.base.manager.IBaseManager;

import io.reactivex.Observable;

public interface INetworkConnectivityHelper extends IBaseManager {

    /**
     * GEt the network status
     * @return {@link Observable<Boolean>} true / false
     */
    Observable<Boolean> getNetworkChanges();
}
