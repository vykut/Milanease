package com.example.milanease.core.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.milanease.R;
import com.example.milanease.core.MainActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private SegmentedControl segmentedControl;
    private Button waterButton;
    private Button electricityButton;
    private Button gasButton;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        initComponents(root);




        return root;
    }

    private void initComponents(View view) {

        segmentedControl = view.findViewById(R.id.dashboard_segmented_control);
//        waterButton = view.findViewById(R.id.water_button);
//        gasButton = view.findViewById(R.id.gas_button);
//        electricityButton = view.findViewById(R.id.water_button);

//        waterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                segmentedControl.toggleUtility(Utility.water);
//            }
//        });
//
//        gasButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                segmentedControl.toggleUtility(Utility.gas);
//            }
//        });
//
//        electricityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                segmentedControl.toggleUtility(Utility.electricity);
//            }
//        });



    }
}