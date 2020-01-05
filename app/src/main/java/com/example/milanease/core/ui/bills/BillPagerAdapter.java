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
import com.example.milanease.data.model.Bill;

import java.util.List;

public class BillPagerAdapter extends PagerAdapter {

    private List<Bill> bills;
    private Context context;
    private BillDelegate billDelegate;

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
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.bill_card, container, false);
        initCard(view, position);
        container.addView(view, 0);
        return view;
    }

    private void initCard(View view, int position) {
        TextView totalCost = view.findViewById(R.id.bill_card_total_cost);
        TextView period = view.findViewById(R.id.bill_card_period);
        TextView totalUsage = view.findViewById(R.id.bill_card_total_usage);
        TextView totalUsageExchanged = view.findViewById(R.id.bill_card_total_usage_exchanged);
        Button btnDownload = view.findViewById(R.id.bill_card_btn_download);
        View container = view.findViewById(R.id.bill_card_container);
        View layoutContainer = view.findViewById(R.id.bill_card_card_view_constraint_layout);

        final Bill bill = bills.get(position);

        switch (bill.getUtility()) {
            case water: container.setBackgroundResource(R.color.utility_water); btnDownload.setBackgroundResource(R.drawable.btn_download_water); layoutContainer.setBackgroundResource(R.drawable.bill_water_border); break;
            case gas: container.setBackgroundResource(R.color.utility_gas); btnDownload.setBackgroundResource(R.drawable.btn_download_gas); layoutContainer.setBackgroundResource(R.drawable.bill_gas_border); break;
            case electricity: container.setBackgroundResource(R.color.utility_electricity); btnDownload.setBackgroundResource(R.drawable.btn_download_electricity); layoutContainer.setBackgroundResource(R.drawable.bill_electricity_border);
        }

        totalCost.setText(bill.displayPrice());
        period.setText(bill.displayPeriod());
        totalUsage.setText(bill.displayQuantity());
        totalUsageExchanged.setText(bill.displayQuantityExchanged());

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billDelegate != null) {
                    billDelegate.download(bill);
                }
            }
        });
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setBillDelegate(BillDelegate billDelegate) {
        this.billDelegate = billDelegate;
    }
}
