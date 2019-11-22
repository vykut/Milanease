package com.example.milanease.core.ui.providers;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.List;

public class ProviderRow extends ConstraintLayout {

    private ImageView logo;
    private TextView name;
    private TextView utilities;

    public ProviderRow(Context context) {
        super(context);
        init(context);
    }

    public ProviderRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProviderRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.provider_row, this);

        logo = findViewById(R.id.provider_image);
        name = findViewById(R.id.provider_name);
        utilities = findViewById(R.id.provider_utilities);
    }

    public void setLogo(int logo) {
        this.logo.setImageResource(logo);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setUtilities(List<Utility> utilities) {
        for(Utility utility : utilities) {
            this.utilities.setText(utility.toString() + ", ");
        }
        this.utilities.setText(this.utilities.getText().subSequence(0, this.utilities.getText().length() - 2));
    }
}
