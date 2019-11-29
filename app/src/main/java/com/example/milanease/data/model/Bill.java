package com.example.milanease.data.model;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.Calendar;
import java.util.Locale;

public class Bill implements Comparable<Bill> {

    private Utility utility;
    private String providerID;
    private double price;
    private Calendar period;
    private double quantity;

    public Bill(Utility utility, String providerID, double price, Calendar period, double quantity) {
        this.utility = utility;
        this.providerID = providerID;
        this.price = price;
        this.period = period;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String displayPrice() {
        return price + "€";
    }

    public String displayPeriod() {
        return period.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public Calendar getPeriod() {
        return period;
    }

    public String getDueDate() {
        period.roll(Calendar.MONTH, true);
        String s = period.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String dueDate = period.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        period.roll(Calendar.MONTH, false);
        return dueDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public String displayQuantity() {
        switch (utility) {
            case electricity: { return Double.valueOf(quantity).intValue() + " kiloWatts / hour"; }
            case water:
            case gas: { return Double.valueOf(quantity).intValue() + " cubic meters";}
            default: { return "Bill.displayQuantity() - default case switch"; }
        }
    }

    public String displayQuantityExchanged() {
        double multiplier = 0;
        String string = "";
        switch (utility) {
            case water: {
                multiplier = 4.227 * 1000;
                string = "cups";
                break;
            }
            case gas: {
                multiplier = 2.345;
                string = "kgs of CO2 released in atmosphere";
                break;
            }
            case electricity: {
                multiplier = 88.1;
                string = "phone charges";
                break;
            }
        }
        return Double.valueOf(quantity * multiplier).intValue() + " " + string;
    }

    public Utility getUtility() {
        return utility;
    }

    public String getProviderID() {
        return providerID;
    }

    @Override
    public int compareTo(Bill o) {
        return period.compareTo(o.getPeriod());
    }
}
