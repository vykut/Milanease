package com.example.milanease.core.ui.Bills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.milanease.R;
import com.example.milanease.core.ui.SegmentedControlInterface;
import com.example.milanease.core.ui.dashboard.SegmentedControl;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.List;

public class BillsFragment extends Fragment implements SegmentedControlInterface {

    private BillsViewModel billsViewModel;
    private ViewPager billsPager;
    private BillPagerAdapter billsAdapter;
    private SegmentedControl segmentedControl;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bills, container, false);

        initComponents(root);

        billsViewModel =
                ViewModelProviders.of(this).get(BillsViewModel.class);
        billsViewModel.init(Utility.water);

        billsViewModel.getSegmentedBills().observe(getViewLifecycleOwner(), new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                initAdapter(bills);
            }
        });

        return root;
    }

    private void initComponents(View root) {
        segmentedControl = root.findViewById(R.id.bills_segmented_control);
        segmentedControl.toggleUtility(Utility.water);
        segmentedControl.setDelegate(this);

        billsPager = root.findViewById(R.id.pager_bills);

        billsPager.setOffscreenPageLimit(3);
    }

    private void initAdapter(List<Bill> bills) {
            billsAdapter = new BillPagerAdapter(bills, getContext());
            billsPager.setAdapter(billsAdapter);
    }

    @Override
    public void stateChanged() {
        billsViewModel.setUtility(segmentedControl.getState());
    }

    @Override
    public void onDestroy() {
        segmentedControl.setDelegate(null);
        super.onDestroy();
    }
}