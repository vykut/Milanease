package com.example.milanease.core.ui.providers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;

public class ProvidersFragment extends Fragment implements ProviderInterface {

    private ProvidersViewModel providersViewModel;
    private RecyclerView providersRecyclerView;
    ProviderAdapter providerAdapter;

    public static String PROVIDER = "Provider";


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

        providerAdapter = new ProviderAdapter(providersViewModel.getProviders().getValue(), getContext());
        providerAdapter.setDelegate(this);
        providersRecyclerView.setAdapter(providerAdapter);

        providersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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