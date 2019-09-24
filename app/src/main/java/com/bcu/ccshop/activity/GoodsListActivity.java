package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bcu.ccshop.R;
import com.bcu.ccshop.dataTranformer.goods;
import com.bcu.ccshop.dataTranformer.icAdapter;
import com.bcu.ccshop.databinding.ActivityGoodsBinding;
import com.bcu.ccshop.databinding.ActivityGoodsListBinding;
import com.bcu.ccshop.model.Goods;
import com.bcu.ccshop.model.goodsInco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpRequest;

public class GoodsListActivity extends AppCompatActivity {

    private ActivityGoodsListBinding binding;

    private ArrayList<Goods> goodsArrayList;

    private List<goodsInco> goodsIncoList;

    private ListView listView;


    private ListAdapter adapter=null;

    private Context context=GoodsListActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        listView=findViewById(R.id.listView);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_goods_list);







    }










    private void getGoodsList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= HttpRequest.get(MainActivity.SERVER_URL+"/goods/select/type/1")
                        .execute().body();
                Gson gson=new Gson();
                goodsArrayList=gson.fromJson(result,new TypeToken<List<goods>>(){}.getType());

                Bitmap bitmap=null;

                for (Goods goods : goodsArrayList) {
                    String image = goods.getGoodsImg().split(";")[0];
                    try {
                         bitmap=getBitmap(image);

                    }
                   catch (Exception e)
                   {
                       e.printStackTrace();
                   }

                    goodsIncoList.add(new goodsInco(goods.getGoodsId(),goods.getGoodsName(),Float.valueOf(goods.getGoodsPrice()),bitmap));

                }

                Message message=new Message();
                handler.sendMessage(message);


            }
        });

    }

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


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           adapter=new icAdapter(goodsIncoList,context,R.layout.itemliset_layout);
           listView.setAdapter(adapter);
        }
    };





}
