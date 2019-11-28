package com.example.milanease.core.ui.bills;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.Calendar;
import java.util.Locale;

public class Bill implements Comparable<Bill> {

    private String providerID;
    private double price;
    private Calendar period;
    private double quantity;
    private Utility utility;

    public Bill(Utility utility) {
        this.providerID = "0001";
        this.price = 75;
        this.period = Calendar.getInstance();
        this.quantity = 550;
        this.utility = utility;
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
        String dueDate = period.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        period.roll(Calendar.MONTH, false);
        return dueDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public String displayQuantity() {
        switch (utility) {
            case electricity: { return Double.valueOf(quantity).intValue() + "kWh"; }
            case water: { return Double.valueOf(quantity).intValue() + "L"; }
            case gas: { return Double.valueOf(quantity).intValue() + "m3";}
            default: { return "Bill.displayQuantity() - default case switch"; }
        }
    }

    public String displayQuantityExchanged() {
        double multiplier = 0;
        String string = "";
        switch (utility) {
            case water: {
                multiplier = 4.227;
                string = "cups";
                break;
            }
            case gas: {
                multiplier = 15.454;
                string = "kgs of CO2 released in atmosphere";
                break;
            }
            case electricity: {
                multiplier = 1.101;
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
