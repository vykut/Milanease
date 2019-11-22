package com.example.milanease.core.ui.providers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;

public class ProvidersFragment extends Fragment {

    private ProvidersViewModel providersViewModel;
    private RecyclerView providersRecyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        providersViewModel =
                ViewModelProviders.of(this).get(ProvidersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_providers, container, false);

        initComponents(root);

        return root;
    }

    private void initComponents(View root) {


        providersRecyclerView = root.findViewById(R.id.provider_recycler_view);

        providersRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        providersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        providersRecyclerView.setAdapter(new ProviderAdapter(providersViewModel.getProviders().getValue()));
    }
}