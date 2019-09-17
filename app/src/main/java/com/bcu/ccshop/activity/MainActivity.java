package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bcu.ccshop.R;

public class MainActivity extends AppCompatActivity {

    public static String SERVER_URL="https://www.2306.tech/CCShop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
