package com.example.milanease.core.ui.Bills;

import com.example.milanease.core.ui.dashboard.Utility;

public class Bill {

    private double price;
    private String period;
    private double quantity;
    private double quantityExchanged;
    private Utility utility;

    public Bill() {
        this.price = 75;
        this.period = "Jan - Mar";
        this.quantity = 550;
        this.quantityExchanged = 2325;
        this.utility = Utility.random();
    }

    public double getPrice() {
        return price;
    }

    public String displayPrice() {
        return price + "€";
    }

    public String getPeriod() {
        return period;
    }

    public double getQuantity() {
        return quantity;
    }

    public String displayQuantity() {
        switch (utility) {
            case electricity: { return quantity + "kW"; }
            case water: { return quantity + "L"; }
            case gas: { return quantity + "Smc";}
            default: { return "Bill.displayQuantity() - default switch"; }
        }
    }

    public double getQuantityExchanged() {
        return quantityExchanged;
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
        return (quantity * multiplier) + " " + string;
    }

    public Utility getUtility() {
        return utility;
    }
}
