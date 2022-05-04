package com.example.starsucks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class OrderDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference starSucksRef = database.getReference("orders");

    private EditText etCustomerName;
    private EditText etCustomerCell;
    private TextView placedOrder;
    private String orderValue;
    private ImageView imgOrderdBeverage;
    private FloatingActionButton fab_order;
    private FloatingActionButton fab_calender;
    private FloatingActionButton fab_cloud;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        order = new Order();
        fab_order = findViewById(R.id.fab_order);
        fab_calender = findViewById(R.id.fab_calender);
        fab_cloud = findViewById(R.id.fab_cloud);
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

        fab_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentHelper.shareIntent(OrderDetailsActivity.this, orderValue);
            }
        });

        fab_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a calender to get today's date
                Calendar datePickerCalender = Calendar.getInstance();
                int year = datePickerCalender.get(Calendar.YEAR);
                int month = datePickerCalender.get(Calendar.MONTH);
                int day = datePickerCalender.get(Calendar.DAY_OF_MONTH);


                //show a datepicker, starting from today's date
                DatePickerDialog ordersDatePicker = new DatePickerDialog(
                        OrderDetailsActivity.this,
                        android.R.style.Theme_Light_Panel,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //set the date of the order once it is picked
                                order.setOrderDate(year + "-" + month + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                ordersDatePicker.show();
            }
        });

        fab_cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerCell = etCustomerCell.getText().toString();
                String customerName = etCustomerName.getText().toString();
                // validate data
                if(
                        !TextUtils.isEmpty(customerName) &&
                        !TextUtils.isEmpty(customerCell) &&
                        !TextUtils.isEmpty(order.getOrderDate()) &&
                        !TextUtils.isEmpty(orderValue)
                ) {
                  order.setProductName(orderValue);
                  order.setCustomerName(customerName);
                  order.setCustomerCell(customerCell);

                  starSucksRef.push().setValue(order);
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}