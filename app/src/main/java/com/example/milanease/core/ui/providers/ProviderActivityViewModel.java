package com.example.milanease.core.ui.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;

public class ProviderActivityViewModel extends ViewModel {

    private MutableLiveData<Integer> mPosition;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init() {
        mPosition = new MutableLiveData<>();
        mPosition.setValue(0);
    }

    public void setPosition(int position) {
        this.mPosition.setValue(position);
    }

    public LiveData<Provider> getProvider() {
        return repositoryManager.getProvider(mPosition.getValue());
    }

    public LiveData<Integer> getPosition() {
        return mPosition;
    }
}