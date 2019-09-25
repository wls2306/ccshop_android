package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.library.BuildConfig;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bcu.ccshop.R;
import com.bcu.ccshop.dataTranformer.Homestay;
import com.bcu.ccshop.dataTranformer.goods;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
    private String[] images;
    private List<String> imgList=new ArrayList<>();
    private ConvenientBanner convenientBanner;
    private TextView hName,hSpec,hSize,hCapacity,hBed,hInfo,hCheckInDate,hCheckOutDate,hDays,hPrice,hADD;
    private String[] xy={"0","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);
        convenientBanner=findViewById(R.id.convenientBanner);
        hName= findViewById(R.id.textView25);
        hSpec= findViewById(R.id.textView37);
        hSize= findViewById(R.id.textView38);
        hCapacity= findViewById(R.id.textView39);
        hBed=findViewById(R.id.textView40) ;
        hInfo= findViewById(R.id.textView42);
        hCheckInDate = findViewById(R.id.guideline27);
        hCheckOutDate= findViewById(R.id.textView30);
        hDays= findViewById(R.id.textView31);
        hPrice=findViewById(R.id.textView43);
        hADD=findViewById(R.id.textView45);


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
                    hName.setText(homestay.gethName());
                    hSpec.setText(homestay.gethRoom()+"室 "+homestay.gethHall()+"厅 "+homestay.gethToilet()+"卫");
                    hSize.setText(homestay.gethSize()+"㎡");
                    hCapacity.setText("可容纳："+homestay.gethPeople()+"人");
                    hBed.setText(homestay.gethBedCount()+"张床");
                    hInfo.setText(homestay.gethDescription());
                    hPrice.setText(homestay.gethPrice()+"元/晚");
                    hADD.setText(homestay.gethAddress());
                    images = jsonObject.getStr("hDetailImage").split(";");
                    xy=jsonObject.getStr("hPosition").split(";");
                    for (String s : images) {
                        s=MainActivity.SERVER_URL+"/"+s;
                        imgList.add(s);
                    }
                    Message m = new Message();
                    handler.handleMessage(m);

                } catch (HttpException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //   System.out.println(JSONUtil.toJsonStr(imgList));
            doRefresh();
        };

    };

    void doRefresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                convenientBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new NetImageHolderView();
                    }
                }, imgList).setPointViewVisible(true)
                        .setPageIndicator(new int[]{R.drawable.icon_no_select, R.drawable.icon_on_select})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            }
        });
    }

    public void goMap(View view){
        Uri uri= Uri.parse("geo:"+xy[0]+","+xy[1]);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }


}

