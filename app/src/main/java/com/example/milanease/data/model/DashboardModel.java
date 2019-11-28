package com.example.milanease.data.model;

import com.example.milanease.core.ui.dashboard.widgets.CostWidget;
import com.example.milanease.core.ui.dashboard.widgets.TodayUsageWidget;
import com.example.milanease.core.ui.dashboard.widgets.TodayVsYesterdayUsageWidget;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardModel {

    @SerializedName("TodayUsageWidget")
    @Expose
    private TodayUsageWidget todayUsageWidget;
    @SerializedName("TodayVsYesterdayUsageWidget")
    @Expose
    private TodayVsYesterdayUsageWidget todayVsYesterdayUsageWidget;
    @SerializedName("CostWidget")
    @Expose
    private CostWidget costWidget;

    public DashboardModel(TodayUsageWidget todayUsageWidget, TodayVsYesterdayUsageWidget todayVsYesterdayUsageWidget, CostWidget costWidget) {
        this.todayUsageWidget = todayUsageWidget;
        this.todayVsYesterdayUsageWidget = todayVsYesterdayUsageWidget;
        this.costWidget = costWidget;
    }

    public TodayUsageWidget getTodayUsageWidget() {
        return todayUsageWidget;
    }

    public void setTodayUsageWidget(TodayUsageWidget todayUsageWidget) {
        this.todayUsageWidget = todayUsageWidget;
    }

    public TodayVsYesterdayUsageWidget getTodayVsYesterdayUsageWidget() {
        return todayVsYesterdayUsageWidget;
    }

    public void setTodayVsYesterdayUsageWidget(TodayVsYesterdayUsageWidget todayVsYesterdayUsageWidget) {
        this.todayVsYesterdayUsageWidget = todayVsYesterdayUsageWidget;
    }

    public CostWidget getCostWidget() {
        return costWidget;
    }

    public void setCostWidget(CostWidget costWidget) {
        this.costWidget = costWidget;
    }

    @Override
    public String toString() {
        return "DashboardModel{" +
                "todayUsageWidget=" + todayUsageWidget +
                ", todayVsYesterdayUsageWidget=" + todayVsYesterdayUsageWidget +
                ", costWidget=" + costWidget +
                '}';
    }
}

