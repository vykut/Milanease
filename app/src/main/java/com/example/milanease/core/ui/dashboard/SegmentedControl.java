package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;

public class SegmentedControl extends ConstraintLayout {

    Button water;
    Button gas;
    Button electricity;

    public SegmentedControl(Context context) {
        super(context);
        init(context, null);
    }

    public SegmentedControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SegmentedControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {
        inflate(context, R.layout.segmented_control, this);

        initComponents();
    }

    private void initComponents() {
        water = findViewById(R.id.water_button);
        gas = findViewById(R.id.gas_button);
        electricity = findViewById(R.id.electricity_button);
    }


}
