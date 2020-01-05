package com.example.milanease.data.viewmodel;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProvidersFragmentViewModel extends ViewModel {

    private LiveData<List<Provider>> mProviders;
    private LiveData<List<Provider>> mUIProviders = new MutableLiveData<>();
    private LiveData<List<Contract>> mContracts;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init() {
        mProviders = repositoryManager.getProviders();
        mContracts = repositoryManager.getContracts();
        mUIProviders = Transformations.switchMap(mContracts, new Function<List<Contract>, LiveData<List<Provider>>>() {
            @Override
            public LiveData<List<Provider>> apply(List<Contract> input) {
                final List<Contract> contracts = input;
                return Transformations.map(mProviders, new Function<List<Provider>, List<Provider>>() {
                    @Override
                    public List<Provider> apply(List<Provider> input) {
                        for (Contract contract: contracts) {
                            for (Provider provider :
                                    input) {
                                
                                if (provider.getId().equals(contract.getProviderID()))
                                    provider.setContract(contract);
                            }
                        }

                        List<Provider> list = new ArrayList<>(input);

                        Collections.sort(list);

                        int flag = -1;
                        for(int i = 0; i < list.size(); i++) {
                            if (list.get(i).getContract() == null) {
                                flag = i;
                                break;
                            }
                        }

                        switch (flag) {
                            case -1: {
                                Provider p = new Provider();
                                p.setName("Your Providers");
                                list.add(0, p);
                                break;
                            }
                            case 0: {
                                Provider p = new Provider();
                                p.setName("Other Providers");
                                list.add(0, p);
                                break;
                            }
                            default: {
                                Provider p = new Provider();
                                p.setName("Your Providers");
                                list.add(0, p);
                                Provider p1 = new Provider();
                                p1.setName("Other Providers");
                                list.add(flag + 1, p1);
                            }
                        }
                        return list;

                    }
                });

            }
        });
    }

    public LiveData<List<Provider>> getProviders() {
        return mProviders;
    }

    public void addProvider(Provider provider) {
        repositoryManager.addProvider(provider);
    }

    public void updateProvider(Provider provider) {
        repositoryManager.updateProvider(provider);
    }

    public void deleteProvider(Provider provider) {
        repositoryManager.deleteProvider(provider);
    }

    public LiveData<List<Provider>> getUIProviders() {
        return mUIProviders;
    }
}