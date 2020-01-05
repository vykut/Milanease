package com.example.milanease.core;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.milanease.core.ui.bills.BillsFragment;
import com.example.milanease.core.ui.dashboard.DashboardFragment;
import com.example.milanease.core.ui.providers.ProvidersFragment;
import com.example.milanease.data.RepositoryManager;
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

        RepositoryManager.getInstance().initDB(getApplicationContext());
    }

    private void reloadInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            selectedFragment = new DashboardFragment();
            setActionBarTitle(getString(R.string.dashboard_tab));
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
                            setActionBarTitle(getString(R.string.bills_tab));
                            break;
                        case R.id.tab_providers:
                            selectedFragment = new ProvidersFragment();
                            setActionBarTitle(getString(R.string.providers_tab));
                            break;
                        case R.id.tab_dashboard:
                            selectedFragment = new DashboardFragment();
                            setActionBarTitle(getString(R.string.dashboard_tab));
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