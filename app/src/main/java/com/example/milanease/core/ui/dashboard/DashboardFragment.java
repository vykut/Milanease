package com.example.milanease.core.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.milanease.R;
import com.example.milanease.core.ui.SegmentedControlInterface;

public class DashboardFragment extends Fragment implements SegmentedControlInterface {

    private DashboardViewModel dashboardViewModel;
    private SegmentedControl segmentedControl;
    private Button waterButton;
    private Button electricityButton;
    private Button gasButton;
    private TodayUsage todayUsage;
    private TodayVsYesterdayUsage todayVsYesterdayUsage;

    private NavController navController;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void initComponents(View view) {
        segmentedControl = view.findViewById(R.id.dashboard_segmented_control);
        segmentedControl.setDelegate(this);
        todayUsage = view.findViewById(R.id.today_usage);
        todayVsYesterdayUsage = view.findViewById(R.id.today_vs_yesterday);

    }

    @Override
    public void onDestroy() {
        segmentedControl.setDelegate(null);
        super.onDestroy();
    }

    @Override
    public void stateChanged() {
        // to implement
    }
}