package com.example.imageproject.di.modules;

import androidx.lifecycle.ViewModel;

import com.example.imageproject.di.mapkey.ViewModelKey;
import com.example.imageproject.image_app.base.ViewModelBase;
import com.example.imageproject.screens.home_page.HomeViewModel;
import com.example.imageproject.screens.main.MainViewModel;

import org.jetbrains.annotations.NotNull;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(@NotNull MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(@NotNull HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelBase.class)
    abstract ViewModel bindViewModelBase(@NotNull ViewModelBase viewModelBase);


}
