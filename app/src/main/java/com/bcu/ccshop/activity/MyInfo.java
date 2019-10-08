package com.bcu.ccshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bcu.ccshop.R;

import static com.bcu.ccshop.activity.MainActivity.themeId;

public class MyInfo extends AppCompatActivity {
    private Activity instance ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(themeId);
        setContentView(R.layout.activity_my_info);
        instance=this;
        Button finsh=findViewById(R.id.button3);
        finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_LONG).show();
                instance.finish();
            }
        });



    }
}
