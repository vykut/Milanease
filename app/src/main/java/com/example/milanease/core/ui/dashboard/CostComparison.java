package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.widgets.CostWidget;

import java.util.Locale;

public class CostComparison extends CardView {

    private TextView finalUsage;
    private TextView finalCost;
    private ProgressBar progressBar;
    private View container;

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
        progressBar = findViewById(R.id.exchange_card_progress_bar);
        container = findViewById(R.id.exchange_card_container);
    }

    public void setFinalUsage(Double usage) {
        this.finalUsage.setText(usage.toString());
    }

    public void setFinalCost(String finalCost) {
        this.finalCost.setText(finalCost);
    }

    public void fetch(boolean fetch) {
        if (fetch) {
            progressBar.setVisibility(VISIBLE);
            container.setVisibility(INVISIBLE);
        } else {
            progressBar.setVisibility(INVISIBLE);
            container.setVisibility(VISIBLE);
        }
    }

    public void updateUI(CostWidget costWidget) {
        String cost = String.format(Locale.getDefault(),"%.2f%s", costWidget.getTotalCost(), costWidget.getCostCurrency());
        finalCost.setText(cost);
        String usage = costWidget.getTotalUsage() + costWidget.getUsageMeasureUnit();
        finalUsage.setText(usage);
    }
}