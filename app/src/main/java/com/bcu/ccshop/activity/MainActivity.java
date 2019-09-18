package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.BaseAdapter;

import com.bcu.ccshop.R;
import com.bcu.ccshop.customWidget.SuperGridView;
import com.bcu.ccshop.dataTranformer.goods;
import com.bcu.ccshop.dataTranformer.icAdapter;
import com.bcu.ccshop.entity.goodsInco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;

public class MainActivity extends AppCompatActivity {

    public static String SERVER_URL="https://www.2306.tech/CCShop";
    private String url="https://www.2306.tech/CCShop/goods/select/index/1001";
    private HashMap paramMap = new HashMap<>();
    private String result;
    private Object lock=new Object();
    private List<goods> topGoodList=new ArrayList<>();
    private SuperGridView goodsView;
    private List<goodsInco> mData ;
    private BaseAdapter mAdapter = null;
    private Context context;
    private static final int COMPLETED = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        goodsView=findViewById(R.id.goodsGridView);
        System.out.println(topGoodList.size());
        mData = new ArrayList<goodsInco>();
        //new Thread(new topgoods(url)).start();
        refresh();

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==COMPLETED){
                mAdapter = new icAdapter(mData,context);
                goodsView.setAdapter(mAdapter);
            }
        }
    };

    public void refresh(){
        context=MainActivity.this;
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
                              List<goods> topgoodsh=gson.fromJson(result,new TypeToken<List<goods>>(){}.getType());
                              topGoodList.addAll(topgoodsh);
                              for(int a=0;a<topGoodList.size();a++)
                              mData.add(new goodsInco(topGoodList.get(a).getGoodsId(),topGoodList.get(a).getGoodsName(),topGoodList.get(a).getGoodsPrice(),topGoodList.get(a).getGoodsImg()));
                              Message message=new Message();
                              message.what=COMPLETED;
                              handler.sendMessage(message);
                      }
                }).start();
    }


}
