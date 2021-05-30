package com.example.imageproject.network.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposables;

public class NetworkConnectivityHelper implements INetworkConnectivityHelper {

    private final Context context;

    private final ConnectivityManager connectivityManager;

    private boolean isConnected;

    private ObservableEmitter<Boolean> emitter = null;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TEST_GAME", "NetworkConnectivityHelper broadcastReceiver onReceive");

            if (isConnected != getConnectivityStatus()) {
                isConnected = !isConnected;
                notifyChange(emitter, isConnected);
            }
        }
    };

    @Inject
    public NetworkConnectivityHelper(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.isConnected = getConnectivityStatus();
        this.context = context;

        this.context.registerReceiver(broadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

    }

    public boolean getConnectivityStatus() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null) {
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                case ConnectivityManager.TYPE_MOBILE:
                    return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        if (broadcastReceiver == null) return;
        try {
            this.context.unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        } catch (Exception exception) {
            // already unregistered
        }
    }

    @Override
    public Observable<Boolean> getNetworkChanges() {
        return Observable.create(emitter -> {
            notifyChange(emitter, isConnected);
            emitter.setDisposable(Disposables.fromAction(this::dispose));
        });
    }

    private void notifyChange(ObservableEmitter<Boolean> emitter, boolean isConnected) {
        if (emitter == null) return;
        this.emitter = emitter;
        this.emitter.onNext(isConnected);
    }

}
