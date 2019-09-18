package com.bcu.ccshop.threadclass;

import android.widget.ListView;

import com.bcu.ccshop.dataTranformer.goods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cn.hutool.http.HttpRequest;
import org.json.JSONArray;


public class topgoods implements Runnable{
    private String url;
    HashMap paramMap = new HashMap<>();
    public topgoods(String url){
        this.url=url;
    }
    public void run(){
        String result2 = HttpRequest.get(url)
                .form(paramMap)
                .timeout(20000)
                .execute().body();
        Gson gson =new Gson();
        List<goods> topgoods=gson.fromJson(result2,new TypeToken<List<goods>>(){}.getType());
    }


}
