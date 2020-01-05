package com.example.milanease.data.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.model.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.RepositoryManager;

import java.io.IOException;
import java.io.OutputStreamWriter;
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

    public void addBill(Bill bill) {
        repositoryManager.addBill(bill);
    }

    public void updateBill(Bill bill) {
        repositoryManager.updateBill(bill);
    }

    public void deleteBill(Bill bill) {
        repositoryManager.deleteBill(bill);
    }

    public void saveBillToFile(Bill bill, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("bills.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(bill.toString());
            Toast.makeText(context, "Bill has be written to file", Toast.LENGTH_SHORT).show();
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Toast.makeText(context, "File write failed: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public MutableLiveData<Utility> getUtility() {
        return mUtility;
    }
}