package com.example.mytodoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav, R.string.close_nav);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();

                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new MonthFragment()).commit();
                    navigationView.setCheckedItem(R.id.nav_month);
                }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_month) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new MonthFragment()).commit();
        } else if (itemId == R.id.nav_year) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new YearFragment()).commit();
        } else if (itemId == R.id.nav_week) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new DayFragment()).commit();
        } else if (itemId == R.id.nav_day) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new WeekFragment()).commit();
        } else if (itemId == R.id.nav_shedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_container, new WeekFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return super.onSupportNavigateUp();
    }

}