package com.example.milanease.core.ui.bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Bill;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BillPagerAdapter extends PagerAdapter {

    private List<Bill> bills;
    private Context context;
    private int cardRes;

    public BillPagerAdapter(List<Bill> bills, Context context, Utility utility) {
        this.bills = bills;
        this.context = context;

        switch (utility) {
            case water: cardRes = R.layout.bill_card_water; break;
            case gas: cardRes = R.layout.bill_card_gas; break;
            case electricity: cardRes = R.layout.bill_card_electricity;
        }
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(cardRes, container, false);
        initCard(view, position);
        container.addView(view, position);
        return view;
    }

    private void initCard(View view, int position) {
        TextView totalCost = view.findViewById(R.id.total_cost);
        TextView period = view.findViewById(R.id.period);
        TextView totalUsage = view.findViewById(R.id.total_usage);
        TextView totalUsageExchanged = view.findViewById(R.id.total_usage_exchanged);
        Button btnDownload = view.findViewById(R.id.btn_download);
        final Bill bill = bills.get(position);

        totalCost.setText(bill.displayPrice());
        period.setText(bill.displayPeriod());
        totalUsage.setText(bill.displayQuantity());
        totalUsageExchanged.setText(bill.displayQuantityExchanged());

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to be implemented
                Toast.makeText(context, bill.displayPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
