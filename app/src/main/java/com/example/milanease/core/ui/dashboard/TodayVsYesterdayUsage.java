package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.milanease.R;



public class TodayVsYesterdayUsage extends CardView {

    private TextView percentage;
    private ImageView upDown;
    private TextView interpretation;

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
        inflate(context, R.layout.today_vs_yesterday, this);

        initComponents();
    }

    private void initComponents() {
        percentage = findViewById(R.id.percent);
        upDown = findViewById(R.id.upDown);
        interpretation = findViewById(R.id.interpretation);
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

}
