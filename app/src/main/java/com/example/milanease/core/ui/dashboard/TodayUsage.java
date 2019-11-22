package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.milanease.R;

public class TodayUsage extends CardView {

    TextView todayQuantity;
    TextView todayMeasureUnit;

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
        todayQuantity = findViewById(R.id.today_quantity_used);
        todayMeasureUnit = findViewById(R.id.today_unit_of_measure);
    }

    public void setTodayQuantity(Double todayQuantity) {
        this.todayQuantity.setText(todayQuantity.toString());
    }

    public void setTodayMeasureUnit(String measureUnit) {
        this.todayMeasureUnit.setText(measureUnit);
    }
}
