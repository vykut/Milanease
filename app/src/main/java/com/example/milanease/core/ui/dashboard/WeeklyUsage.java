package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.widgets.TodayUsageWidget;

import java.util.List;

public class WeeklyUsage extends ConstraintLayout {

    private WeeklyUsageChart weeklyUsageChart;
    private TextView title;

    public WeeklyUsage(Context context) {
        super(context);
        init(context);
    }

    public WeeklyUsage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeeklyUsage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.weekly_usage, this);

        weeklyUsageChart = findViewById(R.id.weekly_usage_chart);
        title = findViewById(R.id.weekly_usage_title);
    }

    public void setWeeklyUsage(List<TodayUsageWidget> weeklyUsage) {
        weeklyUsageChart.setWeeklyUsage(weeklyUsage);

        title.setText(String.format("Past week (%s)", weeklyUsage.get(0).getTodayMeasureUnit()));
        title.setTextColor(getResources().getColor(weeklyUsage.get(0).getUtility().getColor()));
        //hide activity indicator
    }
}
