package com.example.milanease.core.ui.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;

public class ProviderActivityViewModel extends ViewModel {

    private MutableLiveData<Provider> mProvider;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init(Provider provider) {
        mProvider = new MutableLiveData<>();
        mProvider.setValue(provider);
    }

    public LiveData<Provider> getProvider() {
        return mProvider;
    }
}