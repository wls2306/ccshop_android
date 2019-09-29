package com.bcu.ccshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TabHost;

import com.bcu.ccshop.activity.MainActivity;
import com.bcu.ccshop.dataTranformer.Order;
import com.bcu.ccshop.dataTranformer.OrderVO;
import com.bcu.ccshop.dataTranformer.goods;
import com.bcu.ccshop.dataTranformer.icAdapter;
import com.bcu.ccshop.dataTranformer.icOAdapter;
import com.bcu.ccshop.model.goodsInco;
import com.bcu.ccshop.model.orderItemIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;

public class OrderList extends AppCompatActivity {
    private String result;
    private List<OrderVO> payList;
    private List<OrderVO> seedList;
    private List<OrderVO> ckeckList;
    private List<OrderVO> finishList;
    private List<orderItemIcon> mData ;
    private Context context;
    private int type;
    private int COMPLETEDm=5;
    private BaseAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        TabHost tabhost=(TabHost) findViewById(R.id.tb1);
        tabhost.setup();
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("待付款",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("代发货",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("待收货",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("评价/客服",null).setContent(R.id.order_tab));
        Intent intent =getIntent();
        type=intent.getIntExtra("type",0);
        switch (type){
            case 0:{
                tabhost.setCurrentTab(0);
                break;
            }
            case 1:{
                tabhost.setCurrentTab(1);
                break;
            }
            case  2:{
                tabhost.setCurrentTab(2);
                break;
            }
            case  3:{
                tabhost.setCurrentTab(3);
                break;
            }
        }
    }
    @Override
    public void onStart() {

        super.onStart();


    }

    public void getList(String url,List<OrderVO> list){
        context= OrderList.this;
        HashMap paramMap = new HashMap<>();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{ result = HttpRequest.get(url)
                                .form(paramMap)
                                .timeout(20000)
                                .execute().body();
                        } catch (HttpException e) {
                            e.printStackTrace();
                        }
                        Gson gson =new Gson();
                        List<OrderVO> taprooms=gson.fromJson(result,new TypeToken<List<Order>>(){}.getType());
                        try {
                            boolean b = list.addAll(taprooms);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        for(int a=0;a<list.size();a++){
                            String img_url;
                            Bitmap bitmap=null;
                            img_url=list.get(a).getOrderImage();
                            int b=img_url.indexOf(";");
                            img_url=list+"/"+img_url.substring(0,b);
                            try {
                                bitmap = getBitmap(img_url);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mData.add(new orderItemIcon(list.get(a).getOrderId(),list.get(a).getOrderName(),bitmap,list.get(a).getOrderBuyCount(),""+list.get(a).getOrderSinglePrice(),""+list.get(a).getOrderTotalFee(),list.get(a).getCreateTime()));
                        }
                        Message message=new Message();
                        message.what=COMPLETEDm;
                        handler.sendMessage(message);
                    }
                }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==COMPLETEDm){
                mAdapter = new icOAdapter(mData,context,R.layout.order_item);
                goodsView.setAdapter(mAdapter);
            }
        }
    };


    public static Bitmap getBitmap(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }
}
