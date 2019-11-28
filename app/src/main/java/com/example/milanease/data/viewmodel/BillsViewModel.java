package com.example.milanease.data.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.core.ui.bills.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.RepositoryManager;

import java.util.List;

public class BillsViewModel extends ViewModel {

    private LiveData<List<Bill>> mSegmentedBills;
    private MutableLiveData<Utility> mUtility;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init(Utility utility) {
        initUtility(utility);
        initSegmentedBills();
    }

    public LiveData<List<Bill>> getSegmentedBills() {
        return mSegmentedBills;
    }

    public void setUtility(Utility utility) {
        mUtility.setValue(utility);
    }

    private void initUtility(Utility utility) {
        mUtility = new MutableLiveData<>();
        mUtility.setValue(utility);
    }

    private void initSegmentedBills() {
        mSegmentedBills = Transformations.switchMap(mUtility, new Function<Utility, LiveData<List<Bill>>>() {
            @Override
            public LiveData<List<Bill>> apply(Utility input) {
                return repositoryManager.getSegmentedBills(input);
            }
        });
    }
}