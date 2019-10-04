package com.example.appla.plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    private void initUI() {
        findViewById(R.id.denglu).setOnClickListener(this);
        findViewById(R.id.more).setOnClickListener(this);
        findViewById(R.id.bt_user_account).setOnClickListener(this);
        findViewById(R.id.bt_password_account).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.denglu:
                //登录
                intent.setClass(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.more:
                //更多账号
                break;
        }
    }
}
