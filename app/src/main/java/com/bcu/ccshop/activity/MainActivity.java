package com.bcu.ccshop.activity;

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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.bcu.ccshop.R;
import com.bcu.ccshop.customWidget.SuperGridView;
import com.bcu.ccshop.dataTranformer.goods;
import com.bcu.ccshop.dataTranformer.icAdapter;
import com.bcu.ccshop.model.goodsInco;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
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
    private static final int COMPLETEDm = 1;
    private ConvenientBanner convenientBanner;
    private List<Integer> bannerImgs=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //卡项 Tabhost
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        LayoutInflater.from(this).inflate(R.layout.home_page, tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.info_page, tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.mine_page, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("", getDrawable(R.drawable.home_m_s)).setContent(R.id.homepage));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("",getDrawable(R.drawable.type_m_s)).setContent(R.id.infoPage));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("",getDrawable(R.drawable.my_m_s)).setContent(R.id.minePage));


        goodsView=findViewById(R.id.goodsGridView);
        System.out.println(topGoodList.size());
        mData = new ArrayList<goodsInco>();
        convenientBanner=(ConvenientBanner) findViewById(R.id.convenientBanner);
        bannerImgs.add(R.drawable.test_photo1);
        bannerImgs.add(R.drawable.test_photo2);
        tabHost.setFocusable(true);
        tabHost.setFocusableInTouchMode(true);
        tabHost.requestFocus();
        refresh();
        goodsView.setOnItemClickListener(new OnClickItem());
    }

    private class OnClickItem implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this,"ID:"+mData.get(i).getiId(),Toast.LENGTH_SHORT).show();
            Intent intentItem=new Intent(MainActivity.this,GoodsActivity.class);
            intentItem.putExtra("ClickItemID",mData.get(i).getiId());
            startActivity(intentItem);

        }
    }


    private void bannerShow(ConvenientBanner convenientBanner){
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public  Object createHolder() {
                return new LocalImageHolderView();
            }
        },bannerImgs).setPointViewVisible(true)
                .setPageIndicator(new int[]{R.drawable.icon_no_select,R.drawable.icon_on_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .startTurning(2000)
        ;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==COMPLETEDm){
                mAdapter = new icAdapter(mData,context);
                goodsView.setAdapter(mAdapter);
            }
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

    public void refresh(){
        bannerShow(convenientBanner);
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
                              List<goods> taprooms=gson.fromJson(result,new TypeToken<List<goods>>(){}.getType());
                              try {
                                  boolean b = topGoodList.addAll(taprooms);
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }

                          for(int a=0;a<topGoodList.size();a++){
                                  String img_url;
                                  Bitmap bitmap=null;
                                  img_url=topGoodList.get(a).getGoodsImg();
                                  int b=img_url.indexOf(";");
                                  img_url=SERVER_URL+"/"+img_url.substring(0,b);
                                  try {
                                      bitmap = getBitmap(img_url);
                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                                  mData.add(new goodsInco(topGoodList.get(a).getGoodsId(),topGoodList.get(a).getGoodsName(),topGoodList.get(a).getGoodsPrice(),bitmap));
                              }
                              Message message=new Message();
                              message.what=COMPLETEDm;
                              handler.sendMessage(message);
                      }
                }).start();
    }

    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }


}
