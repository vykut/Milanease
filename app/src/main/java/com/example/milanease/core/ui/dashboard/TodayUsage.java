package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.widgets.TodayUsageWidget;
import com.google.gson.internal.Primitives;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TodayUsage extends ConstraintLayout {

    private TextView todayQuantity;
    private TextView todayMeasureUnit;
    private ProgressBar progressBar;
    private View container;
    private CardView cardView;

    public TodayUsage(@NonNull Context context) {
        super(context);
        init(context);
    }

    public TodayUsage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TodayUsage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.today_usage, this);

        initComponents();
    }

    private void initComponents() {
        cardView = findViewById(R.id.today_usage_card_view);
        todayQuantity = findViewById(R.id.today_quantity_used);
        todayMeasureUnit = findViewById(R.id.today_unit_of_measure);
        progressBar = findViewById(R.id.today_usage_progress_bar);
        container = findViewById(R.id.today_usage_container);
    }

    public void setTodayQuantity(Double todayQuantity) {
        this.todayQuantity.setText(todayQuantity.toString());
    }

    public void setTodayMeasureUnit(String measureUnit) {
        this.todayMeasureUnit.setText(measureUnit);
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

    public void updateUI(TodayUsageWidget todayUsageWidget) {
        int usage = Double.valueOf(todayUsageWidget.getTodayQuantity()).intValue();
        todayQuantity.setText(String.valueOf(usage));
        todayMeasureUnit.setText(todayUsageWidget.getTodayMeasureUnit());


        cardView.setCardBackgroundColor(getResources().getColor(todayUsageWidget.getUtility().getColor()));
    }
}
