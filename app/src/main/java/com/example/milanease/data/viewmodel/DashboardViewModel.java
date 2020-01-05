package com.example.milanease.data.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.core.ui.dashboard.widgets.TodayUsageWidget;
import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.DashboardModel;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private LiveData<DashboardModel> mDashboardModel;
    private MutableLiveData<Boolean> mIsFetching;
    private MutableLiveData<Utility> mUtility;
    private LiveData<List<TodayUsageWidget>> mWeeklyUsage;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init(Utility utility) {
        mIsFetching = new MutableLiveData<>();
        mUtility = new MutableLiveData<>();
        mUtility.setValue(utility);
        mDashboardModel = Transformations.switchMap(mUtility, new Function<Utility, LiveData<DashboardModel>>() {
            @Override
            public LiveData<DashboardModel> apply(Utility input) {
                final Utility utility = input;
                return Transformations.map(repositoryManager.getDashboardModel(), new Function<List<DashboardModel>, DashboardModel>() {
                    @Override
                    public DashboardModel apply(List<DashboardModel> input) {
                        mIsFetching.setValue(false);
                        switch (utility) {
                            case water: {
                                return input.get(0);
                            }
                            case electricity: {
                                return input.get(1);
                            }
                            default: {
                                return input.get(2);
                            }
                        }
                    }
                });
            }
        });

        mWeeklyUsage = Transformations.switchMap(mUtility, new Function<Utility, LiveData<List<TodayUsageWidget>>>() {
            @Override
            public LiveData<List<TodayUsageWidget>> apply(Utility input) {
                return repositoryManager.getWeeklyUsage(input);
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

    public LiveData<List<TodayUsageWidget>> getmWeeklyUsage() {
        return mWeeklyUsage;
    }
}