package com.example.milanease.core.ui.dashboard.widgets;

import com.example.milanease.core.ui.dashboard.Utility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayUsageWidget {

    @SerializedName("utility")
    @Expose
    private Utility utility;
    @SerializedName("quantity")
    @Expose
    private double todayQuantity;
    @SerializedName("measureUnit")
    @Expose
    private String todayMeasureUnit;

    public TodayUsageWidget(Utility utility, double todayQuantity, String todayMeasureUnit) {
        this.utility = utility;
        this.todayQuantity = todayQuantity;
        this.todayMeasureUnit = todayMeasureUnit;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public double getTodayQuantity() {
        return todayQuantity;
    }

    public void setTodayQuantity(double todayQuantity) {
        this.todayQuantity = todayQuantity;
    }

    public String getTodayMeasureUnit() {
        return todayMeasureUnit;
    }

    public void setTodayMeasureUnit(String todayMeasureUnit) {
        this.todayMeasureUnit = todayMeasureUnit;
    }

    @Override
    public String toString() {
        return "TodayUsageWidget{" +
                "utility=" + utility +
                ", todayQuantity=" + todayQuantity +
                ", todayMeasureUnit='" + todayMeasureUnit + '\'' +
                '}';
    }
}
