package com.example.imageproject.di;

import com.example.imageproject.di.modules.AppModule;
import com.example.imageproject.di.modules.BuildersModule;
import com.example.imageproject.di.modules.ViewModelFactoryModule;
import com.example.imageproject.di.modules.ViewModelModule;
import com.example.imageproject.di.scope.AppScope;
import com.example.imageproject.image_app.ImageApplication;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                BuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
                ViewModelModule.class
        })
@AppScope
public interface AppComponent extends AndroidInjector<ImageApplication> {
    void inject(@NotNull ImageApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(@NotNull ImageApplication application);

        @NotNull
        AppComponent build();
    }
}
