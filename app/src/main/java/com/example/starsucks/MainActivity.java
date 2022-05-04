package com.example.starsucks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    // imageView elements
    private ImageView sb1;
    private ImageView sb2;
    private ImageView sb3;
    private ImageView sb4;
    private ImageView sb5;
    private ImageView sb6;

    //My public order
    private Order order;

    //Navigation drawer
    private Toolbar toolbar;
    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle toggleOff;

    //Navigation view
    private NavigationView navigationView;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb1:
                order.setProductName("Soy Latte");
                break;
            case R.id.sb2:
                order.setProductName("Chocco Frappe");
                break;
            case R.id.sb3:
                order.setProductName("Bottled Americano");
                break;
            case R.id.sb4:
                order.setProductName("Rainbow Frapp");
                break;
            case R.id.sb5:
                order.setProductName("Caramel Frapp");
                break;
            case R.id.sb6:
                order.setProductName("Black Forest Frapp");
                break;
        }

        Toast.makeText(MainActivity.this, "MMM" + order.getProductName(), Toast.LENGTH_SHORT).show();
        intentHelper.openIntent(this, order.getProductName(), OrderDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_nav_drawer);

        toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerlayout = findViewById(R.id.drawer_layout);
        toggleOff = new ActionBarDrawerToggle(this, drawerlayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggleOff);
        toggleOff.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        sb1 = findViewById(R.id.sb1);
        sb2 = findViewById(R.id.sb2);
        sb3 = findViewById(R.id.sb3);
        sb4 = findViewById(R.id.sb4);
        sb5 = findViewById(R.id.sb5);
        sb6 = findViewById(R.id.sb6);

        sb1.setOnClickListener(this);
        sb2.setOnClickListener(this);
        sb3.setOnClickListener(this);
        sb4.setOnClickListener(this);
        sb5.setOnClickListener(this);
        sb6.setOnClickListener(this);

        order = new Order();
    }

    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_photo:
                intentHelper.openIntent(this, "", CoffeeSnapsActivity.class);
                break;
            case R.id.nav_order_history:
                intentHelper.openIntent(this, "", OrderHistoryActivity.class);
                break;
        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }
}