package com.example.milanease.core.ui.providers;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.Provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProvidersFragmentViewModel extends ViewModel {

    private LiveData<List<Provider>> mProviders;
    private LiveData<List<Provider>> mUIProviders = new MutableLiveData<>();
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init() {
        mProviders = repositoryManager.getProviders();
        mUIProviders = Transformations.map(mProviders, new Function<List<Provider>, List<Provider>>() {
            @Override
            public List<Provider> apply(List<Provider> input) {
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

    public LiveData<List<Provider>> getProviders() {
        return mProviders;
    }

    public void addProvider(Provider provider) {
        repositoryManager.addProvider(provider);
    }

    public LiveData<List<Provider>> getUIProviders() {
        return mUIProviders;
    }
}