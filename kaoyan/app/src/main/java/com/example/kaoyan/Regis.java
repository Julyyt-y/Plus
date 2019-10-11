package com.example.kaoyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaoyan.db.dao.UserDao;

public class Regis extends AppCompatActivity implements View.OnClickListener {
    public EditText editText3;
    public EditText editText4;
    public String pwd;
    public String username;
    public UserDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        initUI2();
        initData2();
    }

    private void initData2() {
    }


    private void initUI2() {
        editText3 = findViewById(R.id.shoujihao);
        editText4 = findViewById(R.id.password);
        findViewById(R.id.regis).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regis:{
                username = editText3.getText().toString().trim();//获取用户名
                pwd = editText4.getText().toString().trim();//获取密码
//        //监听注册
                mDao = new UserDao(getApplicationContext());
                mDao.insert(username, pwd);
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.back:{
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent2);

            }
        }
    }
}
