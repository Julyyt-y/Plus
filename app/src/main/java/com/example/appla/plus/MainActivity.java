package com.example.appla.plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appla.plus.db.dao.UserDao;

//import com.example.appla.plus.db.User;
//import com.example.appla.plus.db.dao.UserDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private EditText ediText1;
    private EditText editText2;

    private Button regis;
    private  String pwd;
    private String username;
    private UserDao mDao;
   private ImageView denglu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        //initData();
    }
//
//    private void initData() {
//      username= editText3.getText().toString();//获取用户名
//        pwd=editText4.getText().toString();//获取密码
//        //监听注册
//        regis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDao=new UserDao(getApplicationContext());
//                mDao.insert(username,pwd);
//                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//

    private void initUI() {
        ediText1=(EditText)findViewById(R.id.bt_user_account);
        //ediText1.setSelection(10);
        editText2=(EditText)findViewById(R.id.bt_password_account);
        //editText2.setSelection(3,10);
        editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        findViewById(R.id.denglu).setOnClickListener(this);

        findViewById(R.id.uregis).setOnClickListener(this);

        //findViewById(R.id.more).setOnClickListener(this);
        findViewById(R.id.bt_user_account).setOnClickListener(this);
        findViewById(R.id.bt_password_account).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.denglu:
                //登录
                //boolean inputText=ediText1.getText().toString().trim().equals("");
                //  inputpassword=editText2.getText().toString().trim().equals("");
               // if(inputText==true||inputpassword==true){
                // String outputText ="登录失败";
               // Toast.makeText(MainActivity.this,outputText,
                       // Toast.LENGTH_LONG).show();
                 String inputText=ediText1.getText().toString().trim();
                String inputpassword=editText2.getText().toString().trim();
                if(inputText.equals("")||inputpassword.equals("")){
                    Toast.makeText(getApplicationContext(),"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                mDao=new UserDao(getApplicationContext());
                if(mDao.find(inputText,inputpassword)) {
                     intent=new Intent(MainActivity.this,FirstActivity.class);
                     startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
                break;

//                //else
//                {
//                    String outputText ="登录成功";
//                    Toast.makeText(MainActivity.this,outputText,
//                            Toast.LENGTH_LONG).show();
//                intent.setClass(getApplicationContext(), FirstActivity.class);
//                startActivity(intent);
//    }
//                break;
//
            case R.id.uregis:
                //注册
                intent.setClass(getApplicationContext(), Regis.class);
                startActivity(intent);
                break;
    }
    }
}
