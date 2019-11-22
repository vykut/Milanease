package com.example.milanease.core.ui.Bills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BillsViewModel extends ViewModel {

    private MutableLiveData<List<Bill>> bills_live_data;
    private List<Bill> bills_list;

    public BillsViewModel() {
        bills_live_data = new MutableLiveData<>();

        initDefaultBills();

        bills_live_data.setValue(bills_list);
    }

    private void initDefaultBills() {
        bills_list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            bills_list.add(new Bill());
        }
    }

    public LiveData<List<Bill>> getBills() {
        return bills_live_data;
    }
}