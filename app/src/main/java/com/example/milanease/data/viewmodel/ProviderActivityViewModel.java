package com.example.milanease.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Provider;

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

    public void addContract(Contract contract) {


        repositoryManager.addContract(contract);
    }

    public void addContract(Provider provider) {
        Contract contract = new Contract(provider.getName() + " Simplu Anual", provider.getId(), repositoryManager.getUserID(), repositoryManager.getUserName());
        repositoryManager.addContract(provider);
        provider.setContract(contract);
        mProvider.setValue(provider);
    }

    public void updateContract(Contract contract) {
        repositoryManager.updateContract(contract);
    }

    public void deleteContract(Contract contract) {
        repositoryManager.deleteContract(contract);
    }
}