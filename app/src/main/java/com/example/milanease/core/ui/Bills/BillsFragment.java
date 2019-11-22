package com.example.milanease.core.ui.Bills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.milanease.R;
import com.example.milanease.core.ui.SegmentedControlInterface;
import com.example.milanease.core.ui.dashboard.SegmentedControl;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillsFragment extends Fragment implements SegmentedControlInterface {

    private BillsViewModel billsViewModel;
    private ViewPager bills_pager;
    private BillPagerAdapter bills_adapter;
    private SegmentedControl segmentedControl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bills, container, false);

        initComponents(root);

        return root;
    }

    private void initComponents(View view) {
        billsViewModel =
                ViewModelProviders.of(this).get(BillsViewModel.class);
        segmentedControl = view.findViewById(R.id.bills_segmented_control);
        segmentedControl.setDelegate(this);

        bills_adapter = initAdapter();

        bills_pager = view.findViewById(R.id.pager_bills);
        bills_pager.setAdapter(bills_adapter);
        bills_pager.setOffscreenPageLimit(bills_adapter.getCount());
//        bills_pager.setPageMargin(-400);
    }

    @Override
    public void onDestroy() {
        segmentedControl.setDelegate(null);
        super.onDestroy();
    }

    private BillPagerAdapter initAdapter() {

        List<Bill> bills = new ArrayList<>();

        for(Bill bill : billsViewModel.getBills().getValue()) {
            if(bill.getUtility().equals(segmentedControl.getState())) {
                bills.add(bill);
            }
        }

        return new BillPagerAdapter(bills, getContext());
    }

    @Override
    public void stateChanged() {
        bills_adapter = initAdapter();
        bills_pager.setAdapter(bills_adapter);
    }
}