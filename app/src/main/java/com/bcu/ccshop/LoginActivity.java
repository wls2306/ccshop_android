package com.bcu.ccshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

public class LoginActivity extends AppCompatActivity {

    private static String LOGINRESULT="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton=(Button)findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView username=(TextView)findViewById(R.id.username);
                TextView password=(TextView)findViewById(R.id.password);
                final Map paramMap=new HashMap<String,String>();
                paramMap.put("username",username.getText().toString());
                paramMap.put("password",password.getText().toString());
                System.out.println("username--"+username.getText().toString());
                System.out.println("username--"+password.getText().toString());


                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        String result = HttpRequest.post(MainActivity.SERVER_URL+"/user/management/login")
                                .header(Header.USER_AGENT, "")//头信息，多个头信息多次调用此方法即可
                                .form(paramMap)//表单内容
                                .timeout(20000)//超时，毫秒
                                .execute().body();
                        LoginActivity.LOGINRESULT=result;

                    }
                }).start();


                try {
                    JSONObject loginObject=new JSONObject(LoginActivity.LOGINRESULT);
                    String rs=(String)loginObject.get("result");
                    Toast toast;
                    if (rs.equals("true")) {
                        toast = Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



            }
        });

    }




}
