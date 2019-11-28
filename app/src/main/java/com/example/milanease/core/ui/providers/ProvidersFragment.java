package com.example.milanease.core.ui.providers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.viewmodel.ProvidersFragmentViewModel;

import java.util.List;

public class ProvidersFragment extends Fragment implements ProviderInterface {

    private ProvidersFragmentViewModel providersFragmentViewModel;
    private RecyclerView providersRecyclerView;
    private ProviderAdapter providerAdapter;

    public static String PROVIDER = "com.example.milanease.core.ui.providers.PROVIDER";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_providers, container, false);

        initComponents(root);

        providersFragmentViewModel =
                ViewModelProviders.of(this).get(ProvidersFragmentViewModel.class);
        providersFragmentViewModel.init();

        providersFragmentViewModel.getUIProviders().observe(getViewLifecycleOwner(), new Observer<List<Provider>>() {
            @Override
            public void onChanged(List<Provider> providers) {
                setProviderAdapter(providers);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initComponents(View root) {
        providersRecyclerView = root.findViewById(R.id.provider_recycler_view);
        providersRecyclerView.setHasFixedSize(true);
        providersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setProviderAdapter(List<Provider> providers) {
        if (providerAdapter == null) {
            providerAdapter = new ProviderAdapter(providersFragmentViewModel.getUIProviders().getValue(), getContext());
            providerAdapter.setDelegate(this);
            providersRecyclerView.setAdapter(providerAdapter);
        } else {
            providerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void providerClicked(Provider provider) {
//        Toast.makeText(getContext(), provider.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ProviderActivity.class);
        intent.putExtra(PROVIDER, provider);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        providerAdapter.setDelegate(null);
        super.onDestroy();
    }
}