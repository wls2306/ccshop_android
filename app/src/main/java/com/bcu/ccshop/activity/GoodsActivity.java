package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bcu.ccshop.R;
import com.bcu.ccshop.databinding.ActivityGoodsBinding;
import com.bcu.ccshop.databinding.ActivityGoodsBindingImpl;
import com.bcu.ccshop.model.Goods;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;

public class GoodsActivity extends AppCompatActivity {
    private static String GOODSURL = "/goods/select/";
    private String goods_id = "";
    private ActivityGoodsBindingImpl binding;
    private Goods goods;
    private ConvenientBanner convenientBanner;
    private String[] images;
    private List<String> imgList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goods);
        convenientBanner = findViewById(R.id.convenientBanner);

        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        goods_id = intent.getStringExtra("ClickItemID");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = HttpRequest.get(MainActivity.SERVER_URL + GOODSURL + goods_id)
                            .timeout(20000)
                            .execute().body();
                    JSONObject jsonObject = JSONUtil.parseObj(result);
                    System.out.println(result);
                    goods = new Goods();
                    goods.setGoodsName(jsonObject.getStr("goodsName").length() < 15 ? jsonObject.getStr("goodsName") : jsonObject.getStr("goodsName").substring(0, 15) + "...");
                    goods.setGoodsPrice(jsonObject.getStr("goodsPrice"));
                    binding.setGoods(goods);
                    images = jsonObject.getStr("goodsImg").split(";");
                    for (String s : images) {
                        s=MainActivity.SERVER_URL+"/"+s;
                        imgList.add(s);
                    }


                    System.out.println("********* Img List :"+JSONUtil.toJsonStr(imgList));



                    Message m = new Message();
                    handler.handleMessage(m);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
         //   System.out.println(JSONUtil.toJsonStr(imgList));
            doRefresh();
        };

    };

    /**
     * 避免 下载图片是 要求 必须主进程报错
     */
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
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                        .startTurning(2000);


            }
        });
    }
}







/* class NetImageHolderView implements Holder<String>{
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

     @Override
     public void UpdateUI(Context context, int position, String data) {
         Looper.prepare();
         Glide.with(context).load(data).into(imageView);
         Looper.loop();
     }
 }*/




