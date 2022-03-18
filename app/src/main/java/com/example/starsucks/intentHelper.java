package com.example.starsucks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class intentHelper {

    public static void openIntent(Context context, String order, Class passTo) {

        Intent i = new Intent(context, passTo);

        i.putExtra("order", order);

        context.startActivity(i);
    }

    public static void shareIntent(Context context, String order) {

        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_TEXT, order);

        sendIntent.setType("text/plain");

        Intent sharedIntent = Intent.createChooser(sendIntent, null);

        context.startActivity(sharedIntent);
    }

    public static void shareIntent(Context context, String productName, String customerName, String customerCell) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        Bundle sharedOrderDetails = new Bundle();
        sharedOrderDetails.putString("productName", productName);
        sharedOrderDetails.putString("customerName", customerName);
        sharedOrderDetails.putString("customerCell", customerCell);

        sendIntent.putExtra(Intent.EXTRA_TEXT, sharedOrderDetails);
        sendIntent.setType("text/plain");

        Intent sharedIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(sharedIntent);
    }
}
