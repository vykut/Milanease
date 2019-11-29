package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.widgets.TodayVsYesterdayUsageWidget;

public class TodayVsYesterdayUsage extends ConstraintLayout {

    private TextView today;
    private TextView yesterday;
    private TextView consumed;
    private TextView percentage;
    private ImageView upDown;
    private TextView interpretation;
    private ProgressBar progressBar;
    private View container;

    public TodayVsYesterdayUsage(@NonNull Context context) {
        super(context);
        init(context);
    }

    public TodayVsYesterdayUsage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TodayVsYesterdayUsage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        inflate(context, R.layout.today_vs_yesterday_linear_layout, this);

        initComponents();
    }

    private void initComponents() {
        percentage = findViewById(R.id.today_vs_yesterday_percent);
        upDown = findViewById(R.id.today_vs_yesterday_up_down);
        interpretation = findViewById(R.id.today_vs_yesterday_interpretation);
        progressBar = findViewById(R.id.today_vs_yesterday_progress_bar);
        container = findViewById(R.id.today_vs_yesterday_container);

        today = findViewById(R.id.today_vs_yesterday_today);
        yesterday = findViewById(R.id.today_vs_yesterday_yesterday);
        consumed = findViewById(R.id.today_vs_yesterday_consumed);
    }

    public void setPercentage(Double percentage) {
        this.percentage.setText(percentage.toString());
    }

    public void setUpDown(boolean up) {

        if (up) {
            upDown.setImageResource(R.drawable.up_24dp);
        } else {
            upDown.setImageResource(R.drawable.down_24dp);
        }
    }

    public void setInterpretation(String interpretation) {
        this.interpretation.setText(interpretation);
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

    private String interpret(boolean moreThanYesterday, Utility utility) {
        if (moreThanYesterday)
            return String.format("more %s than", utility.toString());
        return String.format("less %s than", utility.toString());

    }

    public void updateUI(TodayVsYesterdayUsageWidget todayVsYesterdayUsageWidget) {
        percentage.setText(String.valueOf(todayVsYesterdayUsageWidget.getPercentage()).concat("%"));
        setUpDown(todayVsYesterdayUsageWidget.isMoreThanYesterday());
        interpretation.setText(interpret(todayVsYesterdayUsageWidget.isMoreThanYesterday(), todayVsYesterdayUsageWidget.getUtility()));

        updateTextColor(todayVsYesterdayUsageWidget.getUtility().getColor());

    }

    private void updateTextColor(int color) {
        int textColor = getResources().getColor(color);
        interpretation.setTextColor(textColor);
        percentage.setTextColor(textColor);
        today.setTextColor(textColor);
        yesterday.setTextColor(textColor);
        consumed.setTextColor(textColor);
    }

}