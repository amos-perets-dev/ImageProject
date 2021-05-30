package com.example.imageproject.image_app.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imageproject.network.error.IHandleNetworkError;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ViewModelBase extends ViewModel {

    public MutableLiveData<Integer> showError = new MutableLiveData<>();
    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final IHandleNetworkError handleNetworkError;

    @Inject
    public ViewModelBase(IHandleNetworkError handleNetworkError) {
        this.handleNetworkError = handleNetworkError;
    }

    /**
     * Notify error state
     *
     * @param error exception type
     */
    protected void notifyError(Throwable error) {
        int errorID = handleNetworkError.generateErrorID(error);
        showError.postValue(errorID);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
