package com.example.milanease.core.ui.providers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class SectionRowHolder extends RecyclerView.ViewHolder {

        private SectionRow sectionRow;

        public SectionRowHolder(@NonNull SectionRow sectionRow) {
            super(sectionRow);
            this.sectionRow = sectionRow;
        }
    }

    public static class ProviderRowHolder extends RecyclerView.ViewHolder {

        private ProviderRow providerRow;

        public ProviderRowHolder(@NonNull ProviderRow providerRow) {
            super(providerRow);
            this.providerRow = providerRow;
        }
    }

    private List<Provider> providers;
    private int flag = 0;

    public ProviderAdapter(List<Provider> providers) {
        this.providers = providers;
        Collections.sort(this.providers);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        }

        if (providers.get(position).getContract() == null && providers.get(position - 1).getContract() != null) {
            return 0;
        }

        return 1;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            SectionRow sectionRow = (SectionRow) LayoutInflater.from(parent.getContext()).inflate(R.layout.section_row, parent, false);
            SectionRowHolder sectionRowHolder = new SectionRowHolder(sectionRow);
            return sectionRowHolder;
        }
        ProviderRow providerRow = (ProviderRow) LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_row, parent, false);
        ProviderRowHolder providerRowHolder = new ProviderRowHolder(providerRow);
        return providerRowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            if (position == 0) {
                ((SectionRowHolder) holder).sectionRow.setSection("Your Providers");
            } else {
                ((SectionRowHolder) holder).sectionRow.setSection("Other Providers");
                flag = 1;
            }
        } else {
            ProviderRow providerRow = ((ProviderRowHolder) holder).providerRow;
            providerRow.setLogo(providers.get(position - 1 - flag).getLogo());
            providerRow.setName(providers.get(position - 1 - flag).getName());
            providerRow.setUtilities(providers.get(position - 1 - flag).getUtilities());
        }
    }

    @Override
    public int getItemCount() {
        return providers.size() + 2;
    }
}
