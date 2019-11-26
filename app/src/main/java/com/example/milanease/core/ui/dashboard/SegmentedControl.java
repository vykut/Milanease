package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;
import com.example.milanease.core.ui.SegmentedControlInterface;

public class SegmentedControl extends ConstraintLayout {

    private Button water;
    private Button gas;
    private Button electricity;
    private Utility state;
    @Nullable private SegmentedControlInterface delegate;

    public void setDelegate(SegmentedControlInterface delegate) {
        this.delegate = delegate;
    }

    public SegmentedControl(Context context) {
        super(context);
        init(context);
    }

    public SegmentedControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SegmentedControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.segmented_control, this);
        initComponents();
    }

    private void initComponents() {

        water = findViewById(R.id.water_button);
        gas = findViewById(R.id.gas_button);
        electricity = findViewById(R.id.electricity_button);

        gas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleUtility(Utility.gas);
            }
        });

        water.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleUtility(Utility.water);
            }
        });

        electricity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { toggleUtility(Utility.electricity);
            }
        });
    }

    public void toggleUtility(Utility utility) {
        switch (utility) {
            case water: {
                water.setTextColor(Color.WHITE);
                water.setBackgroundResource(R.drawable.btn_border_selected);
                state = Utility.water;

                toggleButton(Utility.gas);
                toggleButton(Utility.electricity);

                // logic for each fragment
                break;
            }
            case gas: {
                gas.setTextColor(Color.WHITE);
                gas.setBackgroundResource(R.drawable.btn_border_selected);
                state = Utility.gas;

                toggleButton(Utility.water);
                toggleButton(Utility.electricity);

                // logic for each fragment
                break;
            }
            case electricity: {
                electricity.setTextColor(Color.WHITE);
                electricity.setBackgroundResource(R.drawable.btn_border_selected);
                state = Utility.electricity;

                toggleButton(Utility.gas);
                toggleButton(Utility.water);

                // logic for each fragment
                break;
            }
        }

        if(delegate != null) {
            delegate.stateChanged();
        }
    }

    private void toggleButton(Utility utility) {
        switch (utility) {
            case water: {
                water.setBackgroundResource(R.drawable.btn_border);
                water.setTextColor(Color.BLACK);
                break;
            }
            case electricity: {
                electricity.setBackgroundResource(R.drawable.btn_border);
                electricity.setTextColor(Color.BLACK);
                break;
            }
            case gas: {
                gas.setBackgroundResource(R.drawable.btn_border);
                gas.setTextColor(Color.BLACK);
                break;
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        delegate = null;
        super.finalize();
    }

    public Utility getState() {
        return state;
    }
}
