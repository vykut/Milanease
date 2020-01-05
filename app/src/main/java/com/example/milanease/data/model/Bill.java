package com.example.milanease.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.Calendar;
import java.util.Locale;




@Entity(tableName = "bills", foreignKeys = {
        @ForeignKey(entity = Provider.class,
                parentColumns = "id",
                childColumns = "provider_id")
})
public class Bill implements Comparable<Bill> {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "utility")
    private Utility utility;
    @ColumnInfo(name = "provider_id")
    private long providerID;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "period")
    private Calendar period;
    @ColumnInfo(name = "quantity")
    private double quantity;

    @Ignore
    public Bill(Utility utility, long providerID, double price, Calendar period, double quantity) {
        this.utility = utility;
        this.providerID = providerID;
        this.price = price;
        this.period = period;
        this.quantity = quantity;
    }

    public Bill(long id, Utility utility, long providerID, double price, Calendar period, double quantity) {
        this.id = id;
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

    public Long getProviderID() {
        return providerID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int compareTo(Bill o) {
        return period.compareTo(o.getPeriod());
    }
}
