package com.bcu.ccshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.bcu.ccshop.activity.MainActivity.themeId;
import static com.bcu.ccshop.activity.MainActivity.thmemlook;

public class SettingActivity extends AppCompatActivity {
    private RadioButton radioButton1,radioButton2,radioButton3;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(themeId);
        super.onCreate(savedInstanceState);
        this.setTheme(themeId);
        setContentView(R.layout.activity_setting);
        radioButton1=findViewById(R.id.radioButtonGreen);
        radioButton2=findViewById(R.id.radioButtonBule);
        radioButton3=findViewById(R.id.radioButtonPink);
        textView=findViewById(R.id.comEditText);


    }
    @Override
    protected  void onStart() {
        super.onStart();
        switch (themeId){
            case R.style.AppTheme:{
                radioButton1.setChecked(true);
                break;
            }
            case R.style.AppThemeB:{
                radioButton2.setChecked(true);
                break;
            }
            case R.style.AppThemeP:{
                radioButton3.setChecked(true);
                break;
            }
        }
    }

    public void ChangeAppTheme(View view){
        System.out.println("AppTheme");
        if(radioButton1.isChecked()){
            themeId=R.style.AppTheme;
        }else if (radioButton2.isChecked()){
            themeId=R.style.AppThemeB;
        }else if (radioButton3.isChecked()){
            themeId=R.style.AppThemeP;
        }
        System.out.println(themeId);
        synchronized (thmemlook){
            thmemlook.notify();
        }
        recreate();
    }

    public void submet(View view){
        if(textView.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"请输入内容",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"提交成功！",Toast.LENGTH_LONG).show();
        }
    }
}
