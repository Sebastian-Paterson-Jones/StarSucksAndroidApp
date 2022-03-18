package com.example.starsucks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // imageView elements
    private ImageView sb1;
    private ImageView sb2;
    private ImageView sb3;
    private ImageView sb4;
    private ImageView sb5;
    private ImageView sb6;

    //My public order
    private Order order;

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
        setContentView(R.layout.activity_main);
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
}