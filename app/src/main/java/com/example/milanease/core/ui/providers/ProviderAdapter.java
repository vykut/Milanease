package com.example.milanease.core.ui.providers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Provider;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class SectionRowHolder extends RecyclerView.ViewHolder {

        private TextView section;

        public SectionRowHolder(@NonNull View itemView) {
            super(itemView);
            this.section = itemView.findViewById(R.id.section_text_view);
        }

        public void setSectionRow(String section) {
            this.section.setText(section);
        }
    }

    public static class ProviderRowHolder extends RecyclerView.ViewHolder {

        private ImageView logo;
        private TextView name;
        private TextView utilities;
        private ConstraintLayout constraintLayout;

        public ProviderRowHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.provider_image);
            name = itemView.findViewById(R.id.provider_name);
            utilities = itemView.findViewById(R.id.provider_utilities);
            constraintLayout = itemView.findViewById(R.id.provider_row_constraint_layout);
        }

        public void setProviderRow(Provider provider) {
            logo.setImageResource(provider.getLogoLarge());
            name.setText(provider.getName());
            displayUtilities(provider.getUtilities());
        }

        private void displayUtilities(List<Utility> utilities) {
            StringBuilder text = new StringBuilder();
            for(Utility utility : utilities) {
                text.append(utility.toString());
                text.append(", ");
            }
            text.setLength(text.length() - 2);
            this.utilities.setText(text.toString());
        }
    }

    private static int SECTION = 0;
    private static int PROVIDER = 1;

    private List<Provider> providers;
    private Context context;
    @Nullable private ProviderInterface delegate;

    public ProviderAdapter(List<Provider> providers, Context context) {
        this.context = context;
        this.providers = providers;
    }

    @Override
    public int getItemViewType(int position) {
        if (providers.get(position).getLogoLarge() == 0)
            return SECTION;

        return PROVIDER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.section_row, parent, false);
            return new SectionRowHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.provider_row, parent, false);
        return new ProviderRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == SECTION) {
            ((SectionRowHolder) holder).setSectionRow(providers.get(position).getName());
        } else {
            ((ProviderRowHolder) holder).setProviderRow(providers.get(position));
            ((ProviderRowHolder) holder).constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (delegate != null)
                    delegate.providerClicked(providers.get(position));
                }
            });
        }
    }

    @Override
    protected void finalize() throws Throwable {
        delegate = null;
        super.finalize();
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    public void setDelegate(@Nullable ProviderInterface delegate) {
        this.delegate = delegate;
    }
}
