package com.example.imageproject.di.modules;

import com.example.imageproject.screens.home_page.HomeFragment;
import com.example.imageproject.screens.main.MainActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    @NotNull
    public abstract MainActivity bindsMainActivity();


    @ContributesAndroidInjector
    @NotNull
    public abstract HomeFragment bindsHomeFragment();
}
