package com.example.milanease.core.ui.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;

import java.util.ArrayList;
import java.util.List;

public class ProvidersFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Provider>> mProviders;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init() {
        mProviders = repositoryManager.getProviders();
    }

    public LiveData<List<Provider>> getProviders() {
        return mProviders;
    }

    public void addProvider(Provider provider) {
        List<Provider> providers = mProviders.getValue();
        providers.add(provider);
        mProviders.postValue(providers);
    }

}