package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.milanease.R;

public class CostComparison extends CardView {

    private TextView finalUsage;
    private TextView finalCost;

    public CostComparison(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CostComparison(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CostComparison(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        inflate(context, R.layout.exchange_card, this);

        initComponents();
    }

    private void initComponents() {
        finalUsage = findViewById(R.id.final_usage);
        finalCost = findViewById(R.id.final_cost);
    }

    public void setFinalUsage(Double usage) {
        this.finalUsage.setText(usage.toString());
    }

    public void setFinalCost(String finalCost) {
        this.finalCost.setText(finalCost);
    }
}
