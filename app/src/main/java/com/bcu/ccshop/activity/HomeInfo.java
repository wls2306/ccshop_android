package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.bcu.ccshop.R;
import com.bcu.ccshop.dataTranformer.Homestay;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import com.google.android.material.appbar.AppBarLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import static com.bcu.ccshop.activity.MainActivity.themeId;

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
    private int COMPLETED3 = 3;
    private String hPhone;
    private MapView mMapView;
    private AMap aMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(themeId);
        setContentView(R.layout.activity_home_info);
        convenientBanner=findViewById(R.id.convenientBanner);
        hName= findViewById(R.id.textView25);
        hSpec= findViewById(R.id.textView37);
        hSize= findViewById(R.id.textView38);
        hCapacity= findViewById(R.id.textView39);
        hBed=findViewById(R.id.textView40) ;
        hInfo= findViewById(R.id.textView42);
        hCheckInDate = findViewById(R.id.textView29);
        hCheckOutDate= findViewById(R.id.textView30);
        hDays= findViewById(R.id.textView31);
        hPrice=findViewById(R.id.textView43);
        hADD=findViewById(R.id.textView45);
        mMapView=findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        aMap.setTrafficEnabled(false);// 不显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式


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
                    images = jsonObject.getStr("hDetailImage").split(";");
                    xy=jsonObject.getStr("hPosition").split(";");
                    for (String s : images) {
                        s=MainActivity.SERVER_URL+"/"+s;
                        imgList.add(s);
                    }
                    Message message3 = new Message();
                    message3.what=COMPLETED3;
                    handler.sendMessage(message3);
                } catch (HttpException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();




    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //   System.out.println(JSONUtil.toJsonStr(imgList));
            if(msg.what==COMPLETED3){
                hName.setText(homestay.gethName());
                String LinShi;
                LinShi=homestay.gethRoom()+"室 "+homestay.gethHall()+"厅 "+homestay.gethToilet()+"卫";
                hSpec.setText(LinShi);
                hSize.setText(homestay.gethSize()+"㎡");
                hCapacity.setText("可容纳："+homestay.gethPeople()+"人");
                hBed.setText(homestay.gethBedCount()+"张床");
                hInfo.setText(homestay.gethDescription());
                hPrice.setText(homestay.gethPrice()+"元/晚");
                hADD.setText(homestay.gethAddress());
                hPhone=homestay.gethPhone();
                System.out.println("phone"+hPhone);
                LatLng latLng = new LatLng(Double.valueOf(xy[1]),Double.valueOf(xy[0]));
                aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
                final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(homestay.gethName()).snippet("DefaultMarker"));
                aMap. moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                doRefresh();

            }
        }
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
                        //.setPageIndicator(new int[]{R.drawable.icon_no_select, R.drawable.icon_on_select})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            }
        });
    }

    public void goMap(View view){
        Uri uri= Uri.parse("geo:"+xy[1]+","+xy[0]);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    public void goPhone(View view){
        Uri uri= Uri.parse("tel:"+homestay.gethPhone());
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }




}

