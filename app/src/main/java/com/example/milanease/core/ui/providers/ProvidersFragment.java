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

import com.example.milanease.R;

public class ProvidersFragment extends Fragment {

    private ProvidersViewModel providersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        providersViewModel =
                ViewModelProviders.of(this).get(ProvidersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_providers, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
        providersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}