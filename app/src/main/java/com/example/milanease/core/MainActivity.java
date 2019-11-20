package com.example.milanease.core;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.milanease.core.ui.Bills.BillsFragment;
import com.example.milanease.core.ui.dashboard.DashboardFragment;
import com.example.milanease.core.ui.providers.ProvidersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.milanease.R;


public class MainActivity extends AppCompatActivity {


    //maybe save instance of every tab
    private Bundle dashboardInstance = null;
    private Bundle billsInstance = null;
    private Bundle providersInstance = null;

    private Fragment selectedFragment = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getSupportActionBar().hide();
//        } catch (Throwable err) {
//            Toast.makeText(getApplicationContext(), err.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//        }



        BottomNavigationView bottomNav = findViewById(R.id.tab_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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

                    getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.nav_host_fragment,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
