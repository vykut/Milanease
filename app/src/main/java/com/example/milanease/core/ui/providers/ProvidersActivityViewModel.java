package com.example.milanease.core.ui.providers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProvidersActivityViewModel extends ViewModel {

    private MutableLiveData<Provider> mProvider;

    public void init() {

    }

    public MutableLiveData<Provider> getProvider() {
        return mProvider;
    }
}
