package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bcu.ccshop.R;
import com.bcu.ccshop.databinding.ActivityGoodBinding;
import com.bcu.ccshop.model.Goods;
import com.google.gson.Gson;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

public class GoodActivity extends AppCompatActivity {

    private static String GOODS_URL="/goods/select/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);

        Bundle bundle=this.getIntent().getExtras();
        final String id=bundle.getString("id");
        if(StrUtil.isNotBlank(id)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String GoodJson= HttpRequest.get(MainActivity.SERVER_URL+GOODS_URL+id)
                            .timeout(5000)
                            .execute().body();
                    Gson gson=new Gson();
                    Goods g=gson.fromJson(GoodJson,Goods.class);
                    System.out.println(GoodJson);
                }
            }).start();
        }


        ActivityGoodBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_good);
        binding.setLifecycleOwner(this);

    }
}
