package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.library.BuildConfig;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bcu.ccshop.R;
import com.bcu.ccshop.dataTranformer.Homestay;
import com.bcu.ccshop.dataTranformer.goods;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HomeInfo extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private static String HOMESURL = "https://www.2306.tech/CCShop/homestay/select/";
    private String homeID;
    private Homestay homestay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        homeID=intent.getStringExtra("ClickItemID");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = HttpRequest.get(HOMESURL + homeID)
                            .timeout(20000)
                            .execute().body();
                    JSONObject jsonObject = JSONUtil.parseObj(result);
                    System.out.println(result);
                    Gson gson=new Gson();
                    homestay=gson.fromJson(result, new TypeToken<Homestay>(){}.getType());



                } catch (HttpException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

    }


}

