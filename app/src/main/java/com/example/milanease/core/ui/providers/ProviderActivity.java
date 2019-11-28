package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milanease.R;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.viewmodel.ProviderActivityViewModel;

public class ProviderActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView name;
    private ImageButton btnCall;
    private ImageButton btnMessage;
    private TextView description;
    private TextView contractName;
    private ImageButton btnContract;
    private ProviderActivityViewModel providerActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Intent intent = getIntent();
        Provider provider = intent.getParcelableExtra(ProvidersFragment.PROVIDER);

        initComponents();

        providerActivityViewModel = ViewModelProviders.of(this).get(ProviderActivityViewModel.class);
        providerActivityViewModel.init(provider);

        providerActivityViewModel.getProvider().observe(this, new Observer<Provider>() {
            @Override
            public void onChanged(Provider provider) {
                initProviderUI(provider);
            }
        });
    }

    private void initComponents() {
        logo = findViewById(R.id.activity_provider_logo);
        name = findViewById(R.id.activity_provider_name);
        btnCall = findViewById(R.id.activity_provider_btn_call);
        btnMessage = findViewById(R.id.activity_provider_btn_message);
        description = findViewById(R.id.activity_provider_description);
        contractName = findViewById(R.id.activity_provider_contract_name);
        btnContract = findViewById(R.id.activity_provider_contract_btn);
    }

    private void initProviderUI(final Provider provider) {
        logo.setImageResource(provider.getLogoLarge());

        name.setText(provider.getName());
        description.setText(provider.getDescription());
        if (provider.getContract() != null) {
            contractName.setText(provider.getContract().getName());
            btnContract.setImageResource(R.drawable.download_24dp);
        } else {
            contractName.setText((R.string.activity_provider_add_contract));
            btnContract.setImageResource(R.drawable.add_24dp);
        }

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra(ProvidersFragment.PROVIDER, provider);
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + provider.getPhone()));
                startActivity(intent);
            }
        });

        btnContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open form activity
//                providerActivityViewModel.addContract(new Contract());


            }
        });

        setActionBarTitle(provider.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
