package com.example.imageproject.screens.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.imageproject.R;
import com.example.imageproject.di.factory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this, providerFactory).get(MainViewModel.class);

    }

}