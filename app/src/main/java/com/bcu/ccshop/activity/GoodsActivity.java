package com.bcu.ccshop.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bcu.ccshop.OrderList;
import com.bcu.ccshop.R;
import com.bcu.ccshop.databinding.ActivityGoodsBindingImpl;
import com.bcu.ccshop.model.Goods;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.common.base.Strings;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import static com.bcu.ccshop.activity.MainActivity.isLog;
import static com.bcu.ccshop.activity.MainActivity.loglook;
import static com.bcu.ccshop.activity.MainActivity.themeId;
import static com.bcu.ccshop.activity.MainActivity.userID;

public class GoodsActivity extends AppCompatActivity {
    private static String GOODSURL = "/goods/select/";
    private String goods_id = "";
    private ActivityGoodsBindingImpl binding;
    private Goods goods;
    private ConvenientBanner convenientBanner;
    private String[] images;
    private List<String> imgList=new ArrayList<>();
    private Float price;
    private static int LOAD =1;
    private static int SUBMIT =2;
    private TextView unM;
    private ImageButton logButton,setButton,outButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(themeId);
        setContentView(R.layout.activity_goods);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goods);
        convenientBanner = findViewById(R.id.convenientBanner);
        unM=findViewById(R.id.userLevelMian);
        logButton=findViewById(R.id.imageButton9);
        setButton=findViewById(R.id.imageButton10);
        outButton=findViewById(R.id.imageButton11);
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
                    goods.setGoodsDescription(jsonObject.getStr("goodsDescription"));
                    goods.setGoodsSoldAmount(jsonObject.getStr("goodsSoldAmount")+" 人已买");
                    price=Float.valueOf(jsonObject.getStr("goodsPrice"));
                    binding.setGoods(goods);

                    images = jsonObject.getStr("goodsImg").split(";");
                    for (String s : images) {
                        s=MainActivity.SERVER_URL+"/"+s;
                        imgList.add(s);
                    }
                    System.out.println("********* Img List :"+JSONUtil.toJsonStr(imgList));
                    Message m = new Message();
                    m.what= LOAD;
                    handler.handleMessage(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        /**
         * 计算总价
         */
        binding.count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(StrUtil.isNotBlank(charSequence.toString())) {
                    if (Integer.valueOf(charSequence.toString()) >0&& Integer.valueOf(charSequence.toString())<=20) {
                        DecimalFormat decimalFormat=new DecimalFormat(".00");
                        binding.totalPrice.setText(decimalFormat.format(Float.valueOf(charSequence.toString())*price));
                    }
                    else {
                        binding.count.setText("1");
                        Toast.makeText(getApplicationContext(),"购买数量需要大于0小于等于20",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**
         * 确认&提交订单
         */
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLog) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(GoodsActivity.this);
                    builder.setTitle("订单确认");
                    builder.setMessage("请确认您的订单信息：\n商品名："+binding.textView9.getText().toString()+"\n数量："+binding.count.getText().toString()+"\n总价："+binding.totalPrice.getText().toString());
                    builder.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setPositiveButton("提交订单", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO : 提交订单流程
                            submitOrder();
                        }
                    });

                    builder.show();

                }else {
                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_LONG).show();

                   // MainActivity.dolog();
                   /* Intent intent1=new Intent(GoodsActivity.this,LoginActivity.class);
                    startActivity(intent1);*/
                    Intent intent = new Intent(GoodsActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 38);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Intent intentGL = data;
        try {
            userID=intentGL.getStringExtra("username");
            isLog=intentGL.getBooleanExtra("logSe",false);
            if (isLog){
                synchronized (loglook){
                    loglook.notify();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    doRefresh();
                    break;

                case 2:
                    Bundle b=msg.getData();
                    String json=b.getString("result");
                    if (JSONUtil.isJson(json)) {
                        JSONObject jsonObject=JSONUtil.parseObj(json);
                        if (jsonObject.get("result").equals("success")){
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(),"订单提交成功",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(GoodsActivity.this, OrderList.class);
                            intent.putExtra("type",0);
                            intent.putExtra("resultList","https://www.2306.tech/CCShop/order/select/m/"+userID);
                            startActivity(intent);
                            Looper.loop();
                        }else {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(),"订单提交失败",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }

                    break;

            }
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




    public void submitOrder(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 *   orderConsignee: name,
                 *   orderTel: phone,
                 *   orderGoodsId: goodsId,
                 *   orderOpenId: wx.getStorageSync('openid'),
                 *   orderOutletsId: sites.id,
                 *   orderTotalFee: this.data.priceEndAll,
                 *   orderBuyCount: this.data.goodsNum,
                 *   orderScore: orderScore
                 */
                HashMap orderMap=new HashMap<>();
                orderMap.put("orderConsignee",MainActivity.userOpenId);
                orderMap.put("orderGoodsId",goods_id);
                orderMap.put("orderOpenId",MainActivity.userOpenId);
                orderMap.put("orderOutletsId","1001");
                orderMap.put("orderTotalFee",binding.totalPrice.getText().toString());
                orderMap.put("orderBuyCount",binding.count.getText().toString());
                orderMap.put("orderScore","0");
                String result=HttpRequest.get(MainActivity.SERVER_URL+"/order/create")
                        .form(orderMap)
                        .execute().body();

                JSONObject jsonObject=new JSONObject(orderMap);
                System.out.println(jsonObject.toString());

                System.out.println(result);
                Bundle bundle=new Bundle();
                bundle.putString("result",result);
                Message message=new Message();
                message.what=SUBMIT;
                message.setData(bundle);
                handler.handleMessage(message);

            }
        }).start();
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




