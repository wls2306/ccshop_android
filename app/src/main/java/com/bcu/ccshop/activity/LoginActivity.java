package com.bcu.ccshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcu.ccshop.R;

import java.util.ArrayList;
import java.util.HashMap;


import cn.hutool.http.HttpRequest;

public class LoginActivity extends AppCompatActivity {

    private TextView registerButton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     //   ArrayList goods=new ArrayList(Goods);

        Button loginButton = (Button) findViewById(R.id.button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                TextView username = (TextView) findViewById(R.id.username);
                TextView password = (TextView) findViewById(R.id.password);
                final HashMap paramMap = new HashMap<>();
                paramMap.put("username", username.getText().toString());
                paramMap.put("password", password.getText().toString());
                System.out.println("username--" + username.getText().toString());
                System.out.println("username--" + password.getText().toString());


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("*****DO LOGIN*****");
                        String result2= HttpRequest.post(MainActivity.SERVER_URL+"/user/android/login")
                                /*.header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可*/
                                .form(paramMap)//表单内容
                                .timeout(20000)//超时，毫秒
                                .execute().body();

                        Bundle bundle=new Bundle();
                        bundle.putString("result",result2);
                        Message message=new Message();
                        message.setData(bundle);
                        handler.handleMessage(message);

                    }
                }).start();


            }
        });


        registerButton=(TextView)findViewById(R.id.textView3);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });




    }





    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle=new Bundle();
            bundle=msg.getData();
            String rs="";
            String result=bundle.getString("result");
          /*  try {
                JSONObject loginObject = new JSONObject(result);
                 rs = (String) loginObject.get("result");
            }catch (Exception e)
            {
                e.printStackTrace();
            }*/

            Toast toast;
            /*if (rs.equals("true")) {*/
                Looper.prepare();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Looper.loop();

         /*   }*/
        }
    };





}




/**
 *
 */



