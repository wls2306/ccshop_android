package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.bcu.ccshop.R;
import com.bcu.ccshop.dataTranformer.goods;
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
    private Object lock;
    private List<goods> topGoodList=new ArrayList<>();
    private GridView goodsView;
    private List<goodsInco> mData ;
    private BaseAdapter mAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

        //new Thread(new topgoods(url)).start();
        refresh();
        goodsView=findViewById(R.id.goodsGridView);
        System.out.println(topGoodList.size());
        /*mData = new ArrayList<goodsInco>();
        mData.add(new goodsInco(topGoodList.get(0).getGoodsId(),topGoodList.get(0).getGoodsName(),topGoodList.get(0).getGoodsPrice()));*/
    }
    public void refresh(){
        new Thread(new Runnable() {
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
                System.out.println("in:"+topgoodsh.size());
                topGoodList.addAll(topgoodsh);
                System.out.println(topGoodList.size());
            }
        }
        ).start();
    }
}
