package com.example.milanease.core.ui.Bills;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.milanease.R;

public class BillCard extends CardView {

    private TextView totalCost;
    private TextView period;
    private TextView totalUsage;
    private TextView totalUsageExchanged;
    private Button btndownload;

    public BillCard(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BillCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BillCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.bill_card, this);

        initComponents();
    }

    private void initComponents() {
        totalCost = findViewById(R.id.total_cost);
        period = findViewById(R.id.period);
        totalUsage = findViewById(R.id.total_usage);
        totalUsageExchanged = findViewById(R.id.total_usage_exchanged);
        btndownload = findViewById(R.id.btn_download);
    }

}
