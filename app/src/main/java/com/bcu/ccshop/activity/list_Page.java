package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.bcu.ccshop.R;
import com.bcu.ccshop.customWidget.SuperGridView;
import com.bcu.ccshop.dataTranformer.Homestay;
import com.bcu.ccshop.dataTranformer.goods;
import com.bcu.ccshop.dataTranformer.icAdapter;
import com.bcu.ccshop.model.goodsInco;
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

import static com.bcu.ccshop.activity.MainActivity.SERVER_URL;

public class list_Page extends AppCompatActivity {
    private SuperGridView listView;
    private BaseAdapter itemAdapter=null;
    private HashMap paramMap = new HashMap<>();
    private List<goods> goodsList=new ArrayList<>();
    private List<goodsInco> mData= new ArrayList<>() ;
    private List<Homestay> homesList=new ArrayList<>();
    private Context context;
    private  int COMPLETEDm=2;
    private String result;
    private String url_goods;
    private Toolbar toolbar;
    private ImageView imageView;
    private NestedScrollView nestedScrollView;
    private int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        listView=findViewById(R.id.ItemList);
        toolbar=findViewById(R.id.toolbar);
        imageView=findViewById(R.id.app_bar_image);
        nestedScrollView=findViewById(R.id.listScrollView);
        listView.setOnItemClickListener(new OnClickItem());

    }
    private class OnClickItem implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(list_Page.this,"ID:"+mData.get(i).getiId(),Toast.LENGTH_SHORT).show();
            Intent intentItem=new Intent(list_Page.this,GoodsActivity.class);
            intentItem.putExtra("ClickItemID",mData.get(i).getiId());
            startActivity(intentItem);

        }
    }
    @Override
    protected  void  onStart() {
        super.onStart();
        mData.clear();
        goodsList.clear();
        homesList.clear();
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        toolbar.setTitle(title);
        switch (title){
            case "农产品":
                t=1;
                url_goods="https://www.2306.tech/CCShop/goods/select/all";
                break;
            case "民宿":
                t=2;
                url_goods="https://www.2306.tech/CCShop/homestay/select/all";
                break;
            case "其他":
                t=3;
                url_goods="https://www.2306.tech/CCShop/homestay/select/all";
                break;
            default:
                t=0;
                break;

        }
        context=list_Page.this;
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            result = HttpRequest.get(url_goods)
                                    .form(paramMap)
                                    .timeout(20000)
                                    .execute().body();
                        } catch (HttpException e) {
                            e.printStackTrace();
                        }
                        switch (t){
                            case 1:setmDataGoods(result); break;
                            case 2:setmDataHomes(result); break;
                        }

                        Message message=new Message();
                        message.what=COMPLETEDm;
                        handler.sendMessage(message);
                    }
                }
         ).start();
    }

    private void setmDataGoods(String resultG){
        Gson gson = new Gson();
        List<goods> taprooms = gson.fromJson(resultG, new TypeToken<List<goods>>() {}.getType());
        try {
            boolean b = goodsList.addAll(taprooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int a=0;a<goodsList.size();a++){
            String img_url;
            Bitmap bitmap=null;
            img_url=goodsList.get(a).getGoodsImg();
            int b=img_url.indexOf(";");
            img_url=SERVER_URL+"/"+img_url.substring(0,b);
            try {
                bitmap = getBitmapLP(img_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mData.add(new goodsInco(goodsList.get(a).getGoodsId(),goodsList.get(a).getGoodsName(),goodsList.get(a).getGoodsPrice(),bitmap));
        }
    }
    private void setmDataHomes(String resultG){
        Gson gson = new Gson();
        List<Homestay> taprooms = gson.fromJson(resultG, new TypeToken<List<Homestay>>() {}.getType());
        try {
            boolean b = homesList.addAll(taprooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int a=0;a<homesList.size();a++){
            String img_url;
            Bitmap bitmap=null;
            img_url=homesList.get(a).gethTitleImage();
            int b=img_url.indexOf(";");
            img_url=SERVER_URL+"/"+img_url;
            try {
                bitmap = getBitmapLP(img_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mData.add(new goodsInco(homesList.get(a).gethId(),homesList.get(a).gethName(),Float.parseFloat(homesList.get(a).gethPrice()),bitmap));
        }
    }

    public void goCart(View view){
        Toast.makeText(list_Page.this,"Cart",Toast.LENGTH_SHORT).show();
        nestedScrollView.fullScroll(toolbar.getTop());
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==COMPLETEDm){
                itemAdapter=new icAdapter(mData,context,R.layout.itemliset_layout);
                listView.setAdapter(itemAdapter);
            }
        }
    };


    public static Bitmap getBitmapLP(String path) throws IOException {
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
