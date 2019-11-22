package com.example.milanease.core.ui.Bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.viewpager.widget.PagerAdapter;

import com.example.milanease.R;

import java.util.List;

public class BillPagerAdapter extends PagerAdapter {

    private List<Bill> bills;
    private Context context;

    public BillPagerAdapter(List<Bill> bills, Context context) {
        this.bills = bills;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view.equals(object);
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.bill_card, container, false);
        initCard(view, position);
        container.addView(view, 0);
        return view;
    }

    private void initCard(View view, int position) {
        TextView totalCost = view.findViewById(R.id.total_cost);
        TextView period = view.findViewById(R.id.period);
        TextView totalUsage = view.findViewById(R.id.total_usage);
        TextView totalUsageExchanged = view.findViewById(R.id.total_usage_exchanged);
        Bill bill = bills.get(position);

        totalCost.setText(bill.displayPrice());
        period.setText(bill.getPeriod());
        totalUsage.setText(bill.displayQuantity());
        totalUsageExchanged.setText(bill.displayQuantityExchanged());
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
