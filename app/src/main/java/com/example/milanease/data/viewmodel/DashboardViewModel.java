package com.example.milanease.data.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.DashboardModel;

public class DashboardViewModel extends ViewModel {

    private LiveData<DashboardModel> mDashboardModel;
    private MutableLiveData<Boolean> mIsFetching;
    private MutableLiveData<Utility> mUtility;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init(Utility utility) {
        mIsFetching = new MutableLiveData<>();
        mUtility = new MutableLiveData<>();
        mUtility.setValue(utility);
        mDashboardModel = Transformations.switchMap(mUtility, new Function<Utility, LiveData<DashboardModel>>() {
            @Override
            public LiveData<DashboardModel> apply(Utility input) {
                mIsFetching.setValue(false);
                return repositoryManager.getDashboardWidget(input);
            }
        });

        fetchDashboardModel();
    }

    public void fetchDashboardModel() {
        if (mDashboardModel.getValue() == null) {
            mIsFetching.setValue(true);
            repositoryManager.fetchDashboardModel();
        }
    }

    public LiveData<DashboardModel> getDashboardModel() {
        return mDashboardModel;
    }

    public LiveData<Boolean> getIsFetching() {
        return mIsFetching;
    }

    public void setUtility(Utility utility) {
        mUtility.setValue(utility);
    }

    public LiveData<Utility> getUtility() {
        return mUtility;
    }
}