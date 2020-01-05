package com.example.milanease.core.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.milanease.core.ui.dashboard.widgets.TodayUsageWidget;

import java.util.List;

public class WeeklyUsageChart extends View {

    private List<TodayUsageWidget> weeklyUsage;
    private Paint paint = new Paint();

    public WeeklyUsageChart(Context context) {
        super(context);
    }

    public WeeklyUsageChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeeklyUsageChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (weeklyUsage == null)
            return;

        double paddW = getWidth() * 0.1;
        double paddH = getHeight() * 0.1;

        paint.setColor(getResources().getColor(weeklyUsage.get(0).getUtility().getColor()));
        paint.setStrokeWidth(7);
        canvas.drawLine(((float) paddW), (float) paddH, (float) paddW, (float) (getHeight() * 0.9), paint);
        canvas.drawLine((float) paddW, (float) (getHeight() * 0.9), (float) (getWidth() * 0.9), (float) (getHeight() * 0.9), paint);

        double unitHeight = calculateUnitHeight();
        double unitWidth = calculateUnitWidth();

        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize((float) (unitWidth * 0.4));

        for (int i = 0; i < weeklyUsage.size(); i++) {
            paint.setColor(getResources().getColor(weeklyUsage.get(0).getUtility().getColorTransparent()));
            double left = i * unitWidth + paddW;
            double top = getHeight() - paddH - weeklyUsage.get(i).getTodayQuantity() * unitHeight;
            double right = unitWidth + left;
            double bottom = getHeight() - paddH;

            canvas.drawRect((float) left, (float) top, (float) right, (float) bottom, paint);

            float x = (float) (left + unitWidth * 0.1);
            float y = (float) ((top + bottom) / 2);
            paint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(weeklyUsage.get(i).getTodayQuantity()), x, y, paint);
        }
    }

    private double calculateUnitHeight() {
        double max = 0;
        for (TodayUsageWidget todayUsageWidget :
                weeklyUsage) {
            if (todayUsageWidget.getTodayQuantity() > max)
                max = todayUsageWidget.getTodayQuantity();
        }
        return getHeight() * 0.8 / max;
    }

    private double calculateUnitWidth() {
        return getWidth() * 0.8 / weeklyUsage.size();
    }

    public void setWeeklyUsage(List<TodayUsageWidget> weeklyUsage) {
        this.weeklyUsage = weeklyUsage;
        this.invalidate();
    }
}
