package com.example.imageproject.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.example.imageproject.di.factory.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
