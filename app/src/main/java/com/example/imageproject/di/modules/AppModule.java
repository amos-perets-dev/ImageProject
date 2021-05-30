package com.example.imageproject.di.modules;

import com.example.imageproject.R;
import com.example.imageproject.di.scope.AppScope;
import com.example.imageproject.image_app.ImageApplication;
import com.example.imageproject.image_app.base.manager.BaseManager;
import com.example.imageproject.image_app.base.manager.IBaseManager;
import com.example.imageproject.manager.image.IImageManager;
import com.example.imageproject.manager.image.ImageManagerImpl;
import com.example.imageproject.manager.sharedpreferences.IPreferencesManager;
import com.example.imageproject.manager.sharedpreferences.PreferencesManagerImpl;
import com.example.imageproject.network.api.ImageApi;
import com.example.imageproject.network.base.BaseNetworkManagerImpl;
import com.example.imageproject.network.base.IBaseNetworkManager;
import com.example.imageproject.network.error.HandleNetworkError;
import com.example.imageproject.network.error.IHandleNetworkError;
import com.example.imageproject.network.helper.INetworkConnectivityHelper;
import com.example.imageproject.network.helper.NetworkConnectivityHelper;
import com.example.imageproject.repo.ImageRepo;
import com.example.imageproject.repo.ImageRepoImpl;
import com.example.imageproject.utils.files.FilesUtil;
import com.example.imageproject.utils.files.IFilesUtil;
import com.example.imageproject.utils.image.IImageUtil;
import com.example.imageproject.utils.image.ImageUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {ViewModelModule.class}
)
public class AppModule {

    @Provides
    @Singleton
    static IPreferencesManager providePreferencesManager(ImageApplication application) {
        return new PreferencesManagerImpl(application);
    }

    @Provides
    static INetworkConnectivityHelper provideNetworkConnectivityHelper(ImageApplication application) {
        return new NetworkConnectivityHelper(application);
    }

    @Provides
    @Singleton
    static ImageRepo provideImageRepo(IPreferencesManager preferencesManager) {
        return new ImageRepoImpl(preferencesManager);
    }

    @Provides
    static IImageUtil provideImageUtil() {
        return new ImageUtil();
    }

    @Provides
    @Singleton
    static IBaseNetworkManager provideNetworkManager() {
        return new BaseNetworkManagerImpl();
    }

    @Provides
    static IHandleNetworkError provideHandleNetworkError() {
        return new HandleNetworkError();
    }

    @Provides
    static IBaseManager provideBaseManager() {
        return new BaseManager();
    }

    @Provides
    static IFilesUtil provideFilesUtil() {
        return new FilesUtil();
    }

    @Provides
    @Singleton
    static IImageManager provideImageManager(IBaseNetworkManager baseNetworkManager,
                                             ImageRepo imageRepo,
                                             IImageUtil imageUtil,
                                             IFilesUtil filesUtil) {
        return new ImageManagerImpl(
                baseNetworkManager.buildRetrofit().create(ImageApi.class),
                imageRepo,
                imageUtil,
                filesUtil
        );
    }
}
