package com.example.milanease.data.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.model.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.RepositoryManager;

import java.util.ArrayList;
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
                final Utility utility = input;
                return Transformations.map(repositoryManager.getBills(), new Function<List<Bill>, List<Bill>>() {
                    @Override
                    public List<Bill> apply(List<Bill> input) {
                        List<Bill> bills = new ArrayList<>();
                        for (Bill bill : input) {
                            if (bill.getUtility().equals(utility))
                                bills.add(bill);
                        }
                        return bills;
                    }
                });
            }
        });
    }

    public MutableLiveData<Utility> getUtility() {
        return mUtility;
    }
}