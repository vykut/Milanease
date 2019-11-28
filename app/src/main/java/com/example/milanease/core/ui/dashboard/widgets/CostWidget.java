package com.example.milanease.core.ui.dashboard.widgets;

import com.example.milanease.core.ui.dashboard.Utility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CostWidget {

    @SerializedName("utility")
    @Expose
    private Utility utility;
    @SerializedName("totalUsage")
    @Expose
    private int totalUsage;
    @SerializedName("totalCost")
    @Expose
    private double totalCost;
    @SerializedName("usageMeasureUnit")
    @Expose
    private String usageMeasureUnit;
    @SerializedName("costCurrency")
    @Expose
    private String costCurrency;

    public CostWidget(Utility utility, int totalUsage, double totalCost, String usageMeasureUnit, String costCurrency) {
        this.utility = utility;
        this.totalUsage = totalUsage;
        this.totalCost = totalCost;
        this.usageMeasureUnit = usageMeasureUnit;
        this.costCurrency = costCurrency;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public int getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(int totalUsage) {
        this.totalUsage = totalUsage;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getUsageMeasureUnit() {
        return usageMeasureUnit;
    }

    public void setUsageMeasureUnit(String usageMeasureUnit) {
        this.usageMeasureUnit = usageMeasureUnit;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public void setCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
    }

    @Override
    public String toString() {
        return "CostWidget{" +
                "utility=" + utility +
                ", totalUsage=" + totalUsage +
                ", totalCost=" + totalCost +
                ", usageMeasureUnit='" + usageMeasureUnit + '\'' +
                ", costCurrency='" + costCurrency + '\'' +
                '}';
    }
}


