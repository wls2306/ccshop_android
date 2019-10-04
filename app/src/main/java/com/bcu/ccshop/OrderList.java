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
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;

public class OrderList extends AppCompatActivity {
    private String result;
    private List<OrderVO> payList=new ArrayList<>();
    private List<OrderVO> seedList=new ArrayList<>();
    private List<OrderVO> ckeckList=new ArrayList<>();
    private List<OrderVO> finishList=new ArrayList<>();
    private List<orderItemIcon> mData =new ArrayList<>();
    private Context context;
    private int type;
    private int COMPLETEDm=5;
    private ListView payListV;
    private icOAdapter mAdapterOr ;
    private TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        tabhost=(TabHost) findViewById(R.id.tb1);
        tabhost.setup();
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        /*LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());*/
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("待付款",null).setContent(R.id.order_tab));
        /*tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("代发货",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("待收货",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("评价/客服",null).setContent(R.id.order_tab));*/
        payListV=findViewById(R.id.order_tab);
    }
    @Override
    public void onStart() {
        Intent intent =getIntent();
        getList(intent.getStringExtra("resultList")+"/0",payList,0);

        super.onStart();

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

    public void getList(String url,List<OrderVO> list,int tpyei){
        context= OrderList.this;
        HashMap paramMap = new HashMap<>();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("url:"+url);
                        try{ result = HttpRequest.get(url)
                                .form(paramMap)
                                .timeout(20000)
                                .execute().body();
                        } catch (HttpException e) {
                            e.printStackTrace();
                        }
                        System.out.println("res:"+result);
                        Gson gson =new Gson();
                        List<OrderVO> orderrooms=gson.fromJson(result,new TypeToken<List<OrderVO>>(){}.getType());
                        try {
                            boolean c = list.addAll(orderrooms);
                            for(int a=0;a<list.size();a++){
                                String img_url;
                                Bitmap bitmap=null;
                                img_url=list.get(a).getOrderImage();
                                int b=img_url.indexOf(";");
                                img_url="http://www.2306.tech/CCShop/"+img_url;
                                try {
                                    bitmap = getBitmap(img_url);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                mData.add(new orderItemIcon(list.get(a).getOrderId(),list.get(a).getOrderName(),bitmap,list.get(a).getOrderBuyCount(),""+list.get(a).getOrderSinglePrice(),""+list.get(a).getOrderTotalFee(),list.get(a).getCreateTime()));
                            }
                            Message message=new Message();
                            message.what=tpyei;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("context:"+context);
            mAdapterOr = new icOAdapter(mData,context,R.layout.order_item,msg.what);
            payListV.setAdapter(mAdapterOr);
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
