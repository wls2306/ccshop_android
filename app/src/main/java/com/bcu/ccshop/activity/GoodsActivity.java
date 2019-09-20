package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bcu.ccshop.R;
import com.bcu.ccshop.databinding.ActivityGoodsBinding;
import com.bcu.ccshop.model.Goods;
import com.google.gson.Gson;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

public class GoodsActivity extends AppCompatActivity {
    private static String GOODSURL="/goods/select/";
    private String goods_id="";

    ActivityGoodsBinding banding= DataBindingUtil.setContentView(this,R.layout.activity_goods);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        Intent intent=getIntent();
        goods_id=intent.getStringExtra("ClickItemID");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result="";
                try {
                    result=HttpRequest.get(MainActivity.SERVER_URL+GOODSURL+goods_id)
                            .timeout(20000)
                             .execute().body();
                    Gson gson=new Gson();
                    gson.fromJson(result, Goods.class);
                    Goods goods=new Goods();
                    banding.setGoods(goods);
                    System.out.println(result);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
