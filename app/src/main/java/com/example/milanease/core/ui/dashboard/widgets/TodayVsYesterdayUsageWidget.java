package com.example.milanease.core.ui.dashboard.widgets;

import com.example.milanease.core.ui.dashboard.Utility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayVsYesterdayUsageWidget {

    @SerializedName("utility")
    @Expose
    private Utility utility;
    @SerializedName("percentage")
    @Expose
    private int percentage;
    @SerializedName("moreThanYesterday")
    @Expose
    private boolean moreThanYesterday;

    public TodayVsYesterdayUsageWidget(Utility utility, int percentage, boolean moreThanYesterday) {
        this.utility = utility;
        this.percentage = percentage;
        this.moreThanYesterday = moreThanYesterday;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isMoreThanYesterday() {
        return moreThanYesterday;
    }

    public void setMoreThanYesterday(boolean moreThanYesterday) {
        this.moreThanYesterday = moreThanYesterday;
    }

    @Override
    public String toString() {
        return "TodayVsYesterdayUsageWidget{" +
                "utility=" + utility +
                ", percentage=" + percentage +
                ", moreThanYesterday=" + moreThanYesterday +
                '}';
    }
}
