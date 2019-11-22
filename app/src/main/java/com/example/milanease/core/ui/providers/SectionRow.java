package com.example.milanease.core.ui.providers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.milanease.R;

public class SectionRow extends LinearLayout {

    private TextView section;

    public SectionRow(Context context) {
        super(context);
        init(context);
    }

    public SectionRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SectionRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.provider_row, this);

        section = findViewById(R.id.section_text_view);
    }

    public void setSection(String sectionTitle) {
        this.section.setText(sectionTitle);
    }
}
