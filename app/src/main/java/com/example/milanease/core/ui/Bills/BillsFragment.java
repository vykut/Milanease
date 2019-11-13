package com.example.milanease.core.ui.Bills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.milanease.R;

public class BillsFragment extends Fragment {

    private BillsViewModel billsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        billsViewModel =
                ViewModelProviders.of(this).get(BillsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bills, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        billsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}