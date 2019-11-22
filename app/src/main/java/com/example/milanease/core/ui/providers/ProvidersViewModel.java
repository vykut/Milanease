package com.example.milanease.core.ui.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProvidersViewModel extends ViewModel {

    private MutableLiveData<List<Provider>> providersMutableData;
    private List<Provider> providersList;

    public ProvidersViewModel() {
        providersMutableData = new MutableLiveData<>();
        initProviders();
        providersMutableData.setValue(providersList);
    }

    private void initProviders() {
        providersList = new ArrayList<>();

        providersList.add(new Provider(true));
        providersList.add(new Provider(true));
        providersList.add(new Provider(false));
        providersList.add(new Provider(false));
        providersList.add(new Provider(false));
        providersList.add(new Provider(false));
    }

    public LiveData<List<Provider>> getProviders() {
        return providersMutableData;
    }
}