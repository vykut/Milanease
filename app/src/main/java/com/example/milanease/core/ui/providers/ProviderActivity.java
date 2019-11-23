package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milanease.R;

import java.nio.file.CopyOption;

public class ProviderActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView name;
    private ImageButton btnCall;
    private ImageButton btnMessage;
    private TextView description;
    private TextView contractName;
    private ImageButton btnContract;
    private Provider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Intent intent = getIntent();
        provider = intent.getParcelableExtra(ProvidersFragment.PROVIDER);

        initComponents();

        initProviderUI();
    }

    private void initComponents() {
        logo = findViewById(R.id.activity_provider_logo);
        name = findViewById(R.id.activity_provider_name);
        btnCall = findViewById(R.id.activity_provider_btn_call);
        btnMessage = findViewById(R.id.activity_provider_btn_message);
        description = findViewById(R.id.activity_provider_description);
        contractName = findViewById(R.id.activity_provider_contract_name);
        btnContract = findViewById(R.id.activity_provider_contract_btn);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra(ProvidersFragment.PROVIDER, provider);
                startActivity(intent);
            }
        });

        btnContract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + provider.getPhone()));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Calling " + provider.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        setActionBarTitle(provider.getName());
    }

    private void initProviderUI() {
        logo.setImageResource(provider.getLogoLarge());

        name.setText(provider.getName());
        description.setText(provider.getDescription());
        if (provider.getContract() != null) {
            contractName.setText(provider.getContract().getName());
            btnContract.setImageResource(R.drawable.download_24dp);
        } else {
            contractName.setText("Add a contract now");
            btnContract.setImageResource(R.drawable.add_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
