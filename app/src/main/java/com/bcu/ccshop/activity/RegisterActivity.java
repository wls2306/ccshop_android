package com.bcu.ccshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bcu.ccshop.R;

import java.util.HashMap;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

public class RegisterActivity extends AppCompatActivity {

    Log log= LogFactory.get();
    private Activity instance ;
    private View viewQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button submitButton=(Button)findViewById(R.id.button2);

        final EditText userIdText=(EditText)findViewById(R.id.editText) ;

        final EditText pwdText=(EditText)findViewById(R.id.editText2);

        final EditText configPwdText=(EditText)findViewById(R.id.editText3);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewQ=view;

                submitButton.setEnabled(false);

                String userId=userIdText.getText().toString();

                String userPwd=pwdText.getText().toString();

                String configPwd=configPwdText.getText().toString();

                final HashMap map=new HashMap<>();

                map.put("userId",userId);

                map.put("userOpenid",userId);

                map.put("userPassword",userPwd);

                map.put("userType","1");

                map.put("userStatus","1");

                if (StrUtil.isNotEmpty(userId)&&StrUtil.isNotEmpty(userPwd)&&StrUtil.isNotEmpty(configPwd)
                    &&userPwd.equals(configPwd))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("*****DO REGISTER*****");
                            String result2 = HttpRequest.post(MainActivity.SERVER_URL+"/user/insert")
                                    /*.header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可*/
                                    .form(map)//表单内容
                                    .timeout(20000)//超时，毫秒
                                    .execute().body();

                            Bundle bundle=new Bundle();
                            bundle.putString("result",result2);
                            Message message=new Message();
                            message.setData(bundle);
                            handler.handleMessage(message);

                        }
                    }).start();
                }else
                {
                    Toast.makeText(getApplicationContext(),"输入项为空或两次密码输入不一致！",Toast.LENGTH_LONG).show();
                    submitButton.setEnabled(true);
                }
            }
        });


        ActionBar actionBar=getActionBar();
        if (actionBar!=null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressLint("HandlerLeak")
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle=new Bundle();
            bundle=msg.getData();
            //Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            String result=bundle.getString("result");

            log.info(result);

            if (StrUtil.isNotEmpty(result)) {
                JSONObject json=new JSONObject(result);

                String rs=json.getStr("result");

                final AlertDialog.Builder alert=new AlertDialog.Builder(RegisterActivity.this);
                alert.setTitle("提示");
                alert.setNegativeButton("好的",null);

                log.info("注册信息返回：{}, RESULT={}",result,json.get(result));

                if (rs.equals("true")) {
                    //TODO: 注册成功后的处理
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG);
                    backb(viewQ);
                    Looper.loop();


                }else
                {
                    Looper.prepare();
                    alert.setMessage("注册失败，手机号已存在").show();
                    Button submitButton=(Button)findViewById(R.id.button2) ;
                    submitButton.setEnabled(true);
                    Looper.loop();
                }
            }else
            {
                Looper.prepare();
                Toast.makeText(getApplicationContext(),"注册失败，手机号已存在",Toast.LENGTH_LONG).show();
                Looper.loop();
            }

        }
    };
    public void backb(View view){
        instance = this;
        instance.finish();
    }
}
