package com.example.milanease.core.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.milanease.R;
import com.example.milanease.core.ui.SegmentedControlInterface;
import com.example.milanease.data.model.DashboardModel;
import com.example.milanease.data.viewmodel.DashboardViewModel;

public class DashboardFragment extends Fragment implements SegmentedControlInterface {

    private DashboardViewModel dashboardViewModel;
    private SegmentedControl segmentedControl;
    private TodayUsage todayUsage;
    private TodayVsYesterdayUsage todayVsYesterdayUsage;
    private CostComparison exchangeCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        dashboardViewModel.init(Utility.water);

        dashboardViewModel.getDashboardModel().observe(getViewLifecycleOwner(), new Observer<DashboardModel>() {
            @Override
            public void onChanged(DashboardModel dashboardModel) {
                updateWidgets(dashboardModel);
            }
        });

        dashboardViewModel.getIsFetching().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                    fetchWidgets(aBoolean);
            }
        });

        initComponents(root);


        return root;
    }

    private void initComponents(View view) {
        segmentedControl = view.findViewById(R.id.dashboard_segmented_control);
        segmentedControl.toggleUtility(Utility.water);
        segmentedControl.setDelegate(this);

        todayUsage = view.findViewById(R.id.today_usage);
        todayVsYesterdayUsage = view.findViewById(R.id.today_vs_yesterday);
        exchangeCard = view.findViewById(R.id.exchange_card);
    }

    private void fetchWidgets(boolean fetch) {
        todayUsage.fetch(fetch);
        todayVsYesterdayUsage.fetch(fetch);
        exchangeCard.fetch(fetch);
    }

    private void updateWidgets(DashboardModel dashboardModel) {
        todayUsage.updateUI(dashboardModel.getTodayUsageWidget());
        todayVsYesterdayUsage.updateUI(dashboardModel.getTodayVsYesterdayUsageWidget());
        exchangeCard.updateUI(dashboardModel.getCostWidget());


    }

    @Override
    public void onDestroy() {
        segmentedControl.setDelegate(null);
        super.onDestroy();
    }

    @Override
    public void stateChanged() {
        dashboardViewModel.setUtility(segmentedControl.getState());
    }
}