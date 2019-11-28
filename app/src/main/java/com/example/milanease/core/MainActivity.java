package com.example.milanease.core;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.milanease.core.ui.bills.BillsFragment;
import com.example.milanease.core.ui.dashboard.DashboardFragment;
import com.example.milanease.core.ui.providers.ProvidersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.milanease.R;

public class MainActivity extends AppCompatActivity {

    private Fragment selectedFragment = null;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.tab_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        reloadInstance(savedInstanceState);
    }

    private void reloadInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            selectedFragment = new DashboardFragment();
            setActionBarTitle("Dashboard");
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFragment).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == bottomNavigationView.getSelectedItemId())
                        return false;

                    switch (item.getItemId()) {
                        case R.id.tab_bills:
                            selectedFragment = new BillsFragment();
                            setActionBarTitle("Bills");
                            break;
                        case R.id.tab_providers:
                            selectedFragment = new ProvidersFragment();
                            setActionBarTitle("Providers");
                            break;
                        case R.id.tab_dashboard:
                            selectedFragment = new DashboardFragment();
                            setActionBarTitle("Dashboard");
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}