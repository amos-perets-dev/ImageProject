package com.example.imageproject.screens.home_page;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.imageproject.R;
import com.example.imageproject.data.Result;
import com.example.imageproject.di.factory.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;
    private ImageView image;
    private ProgressBar progressBarLoader;

    @Inject
    HomeViewModel homeViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(getViewModelStore(), providerFactory).get(HomeViewModel.class);

        image = view.findViewById(R.id.image_view_image);
        progressBarLoader = view.findViewById(R.id.loadData);

        homeViewModel
                .imageNotifier.observe(getViewLifecycleOwner(), this::setState);

        homeViewModel
                .showError.observe(getViewLifecycleOwner(), this::showError);
    }

    /**
     * Set the new state
     *
     * @param state {@link Result}
     */
    private void setState(Result state) {
        if (state instanceof Result.Start) {
            startLoad();
        } else {
            finishLoad(state);
        }
    }

    /**
     * Finish the load state
     * @param state {@link Result.Error} / {@link Result.Success}
     */
    private void finishLoad(Result state) {
        progressBarLoader.animate().scaleX(0).scaleY(0).setDuration(100);
        image.setScaleX(1F);
        image.setScaleY(1F);

        if (state instanceof Result.Success) {
            setSuccessState((Result.Success) state);
        } else if (state instanceof Result.Error) {
            setErrorState((Result.Error) state);
        }

    }

    private void setErrorState(Result.Error state) {
        image.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), state.getImage()));
    }

    private void setSuccessState(Result.Success state) {
        image.setImageBitmap(state.getImage());
    }

    private void startLoad() {
        clearImage();
        image.animate().scaleX(1).scaleY(1).setDuration(1500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressBarLoader.animate().scaleX(1).scaleY(1).setDuration(500).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }

    /**
     * Remove the previous image
     */
    private void clearImage() {
        image.setImageDrawable(null);
    }

    private void showError(Integer errorId) {
        Log.d("TEST_GAME", "HomeFragment showError");

        String errorText = getString(errorId);
        Snackbar.make(requireView(), errorText, Snackbar.LENGTH_LONG).show();
    }

}