package com.example.starsucks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OrderDetailsActivity extends AppCompatActivity {

    private EditText etCustomerName;
    private EditText etCustomerCell;
    private TextView placedOrder;
    private String orderValue;
    private ImageView imgOrderdBeverage;
    private FloatingActionButton fab;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order = new Order();
        fab = findViewById(R.id.fab_order);
        placedOrder = findViewById(R.id.tv_placedOrder);
        etCustomerName = findViewById(R.id.et_customerName);
        etCustomerCell = findViewById(R.id.et_customerCell);
        imgOrderdBeverage = findViewById(R.id.img_orderedBeverage);

        orderValue = getIntent().getStringExtra("order");
        placedOrder.setText(orderValue);

        switch (orderValue) {
            case "Soy Latte":
                imgOrderdBeverage.setImageResource(R.drawable.sb1);
                break;
            case "Chocco Frappe":
                imgOrderdBeverage.setImageResource(R.drawable.sb2);
                break;
            case "Bottled Americano":
                imgOrderdBeverage.setImageResource(R.drawable.sb3);
                break;
            case "Rainbow Frapp":
                imgOrderdBeverage.setImageResource(R.drawable.sb4);
                break;
            case "Caramel Frapp":
                imgOrderdBeverage.setImageResource(R.drawable.sb5);
                break;
            case "Black Forest Frapp":
                imgOrderdBeverage.setImageResource(R.drawable.sb6);
                break;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentHelper.shareIntent(OrderDetailsActivity.this, orderValue);
            }
        });
    }
}