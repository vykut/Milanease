package com.example.milanease.core.ui.Bills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;

import java.util.ArrayList;
import java.util.List;

public class BillsViewModel extends ViewModel {

    private MutableLiveData<List<Bill>> mBills;
    private RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public void init() {
        mBills = repositoryManager.getBills();
    }

    public LiveData<List<Bill>> getBills() {
        return mBills;
    }

    public void addNewBill(Bill bill) {
        List<Bill> bills = mBills.getValue();
        bills.add(bill);
        mBills.postValue(bills);
    }
}