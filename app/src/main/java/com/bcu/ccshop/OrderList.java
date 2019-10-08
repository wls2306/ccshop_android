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
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bcu.ccshop.activity.MainActivity;
import com.bcu.ccshop.activity.list_Page;
import com.bcu.ccshop.customWidget.LoadingDialog;
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

import static com.bcu.ccshop.activity.MainActivity.themeId;

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
    private View nullView;
    private TextView textview1,textview2,textview3,textview4;
    private Toolbar toolbar;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(themeId);
        setContentView(R.layout.activity_order_list);
        tabhost=(TabHost) findViewById(R.id.tb1);
        tabhost.setup();
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.order_tab, tabhost.getTabContentView());
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("tab1",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("tab2",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("tab3",null).setContent(R.id.order_tab));
        tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("tab4",null).setContent(R.id.order_tab));
        textview1= tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        textview2= tabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        textview3= tabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        textview4= tabhost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        payListV=findViewById(R.id.order_tab);
        nullView = getLayoutInflater().inflate(R.layout.null_item_view, null);
        toolbar=findViewById(R.id.toolbar3);
    }
    @Override
    public void onStart() {
        Intent intent =getIntent();
        String urlRe=intent.getStringExtra("resultList");
        type=intent.getIntExtra("type",-1);
        int listType=intent.getIntExtra("listTpye",-1);
        switch (listType){
                case 0:{
                    toolbar.setTitle("农产品订单");
                    textview1.setText("待付款");
                    textview2.setText("代发货");
                    textview3.setText("待收货");
                    textview4.setText("评价/客服");
                    switch (type){
                        case 0:{ tabhost.setCurrentTab(1);tabhost.setCurrentTab(0);getList(urlRe+"/"+type,payList,0);break; }
                        case 4:{ tabhost.setCurrentTab(1);getList(urlRe+"/"+type,payList,1);break; }
                        case 6:{ tabhost.setCurrentTab(2);getList(urlRe+"/"+type,payList,2);break; }
                        case 7:{ tabhost.setCurrentTab(3);getList(urlRe+"/"+type,payList,3);break; }
                    }
                break;
            }
                case 1:{
                    toolbar.setTitle("民宿订单");
                    textview1.setText("待支付");
                    textview2.setText("带入住");
                    textview3.setText("已完成");
                    textview4.setText("评价/客服");
                    switch (type){
                        case 0:{ tabhost.setCurrentTab(1);tabhost.setCurrentTab(0);getList(urlRe+"/"+type,payList,0);break; }
                        case 1:{ tabhost.setCurrentTab(1);getList(urlRe+"/"+type,payList,1);break; }
                        case 3:{ tabhost.setCurrentTab(2);getList(urlRe+"/"+type,payList,2);break; }
                        case 2:{ tabhost.setCurrentTab(3);getList(urlRe+"/"+type,payList,3);break; }
                    }
                break;
            }
            default:{
                break;
            }
        }
        super.onStart();


        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (listType){
                    case 0:{
                        switch (tabId){
                            case "tab1":{ getList(urlRe+"/0",payList,0); System.out.println("tabId"+tabId); break;}
                            case "tab2":{ getList(urlRe+"/4",payList,1); System.out.println("tabId"+tabId); break;}
                            case "tab3": {getList(urlRe+"/6",payList,2); System.out.println("tabId"+tabId); break;}
                            case "tab4":{ getList(urlRe+"/7",payList,3); System.out.println("tabId"+tabId); break;}
                        }
                        break;
                    }
                    case 1:{
                        switch (tabId){
                            case "tab1":{ getList(urlRe+"/0",payList,0); System.out.println("tabId"+tabId); break;}
                            case "tab2":{ getList(urlRe+"/1",payList,1); System.out.println("tabId"+tabId); break;}
                            case "tab3": {getList(urlRe+"/3",payList,2); System.out.println("tabId"+tabId); break;}
                            case "tab4":{ getList(urlRe+"/4",payList,3); System.out.println("tabId"+tabId); break;}
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });


    }

    public void getList(String url,List<OrderVO> list,int tpyei){
        loadingDialog.showDialogForLoading(OrderList.this);
        context= OrderList.this;
        mData.clear();
        payList.clear();

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
            System.out.println("context:"+context+"   | mData.size="+mData.size());
            if(mData.size()==0){
                if(payListV.getHeaderViewsCount()<1){
                    System.out.println(payListV.getHeaderViewsCount());
                    payListV.addHeaderView(nullView);
                }
            }else {
                if(payListV.getHeaderViewsCount()>=1){
                    payListV.removeHeaderView(nullView);
                }
            }
            mAdapterOr = new icOAdapter(mData,context,R.layout.order_item,msg.what);
            mAdapterOr.notifyDataSetChanged();
            payListV.setAdapter(mAdapterOr);
            mAdapterOr.notifyDataSetChanged();
            loadingDialog.cancelDialogForLoading();
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
