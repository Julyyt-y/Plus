package com.example.appla.plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appla.plus.http.HttpUtil;
import com.example.appla.plus.tagcloud.TagTextActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class CloudContextActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_context);

        Button xueXiaoJianJie,kaoShiKeMu, baoLuBi, fuLuBi, tuiMainBiLi,
                jiuYeLv, fenShuXian, kaoShiDaGang,zhuanXueShuo;

        xueXiaoJianJie = findViewById(R.id.xuexiaojianjie);
        kaoShiKeMu = findViewById(R.id.kaoshikemu);
        baoLuBi = findViewById(R.id.baolubi);
        fuLuBi = findViewById(R.id.fulubi);
        tuiMainBiLi = findViewById(R.id.tuimianbili);
        jiuYeLv = findViewById(R.id.jiuyelv);
        fenShuXian = findViewById(R.id.fenshuxian);
        kaoShiDaGang = findViewById(R.id.kaoshidagang);
        zhuanXueShuo = findViewById(R.id.zhuanxueshuo);

        xueXiaoJianJie.setOnClickListener(this);
        kaoShiKeMu.setOnClickListener(this);
        baoLuBi.setOnClickListener(this);
        fuLuBi.setOnClickListener(this);
        tuiMainBiLi.setOnClickListener(this);
        jiuYeLv.setOnClickListener(this);
        fenShuXian.setOnClickListener(this);
        kaoShiDaGang.setOnClickListener(this);
        zhuanXueShuo.setOnClickListener(this);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.xuexiaojianjie:
                Intent intent1 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent1);
                break;
            case R.id.kaoshikemu:
                Intent intent2 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent2);
                break;
            case R.id.baolubi:
                Intent intent3 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent3);
                break;
            case R.id.fulubi:
                Intent intent4 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent4);
                break;
            case R.id.tuimianbili:
                Intent intent5 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent5);
                break;
            case R.id.jiuyelv:
                Intent intent6 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent6);
                break;
            case R.id.fenshuxian:
                Intent intent7 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent7);
                break;
            case R.id.kaoshidagang:
                Intent intent8 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent8);
                break;
            case R.id.zhuanxueshuo:
                Intent intent9 = new Intent(CloudContextActivity.this,TagTextActivity.class);
                startActivity(intent9);
                break;
            default:
                break;
        }
    }

//    private void sendRequestWithOkHttp(){
//        //开启线程，发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("")    //服务器地址
//                            .build();
//                    Response response = client.newCall(request).execute();//创建Call对象并调用execute()方法发送请求并获取服务器返回的数据
//                    String responseData = response.body().string();
//                    parseJSONWithJSONObject(responseData);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    //从服务器获取到的解析数据
//    private void parseJSONWithJSONObject(String jsonData){
//        Gson gson = new Gson();
//        List<Information> list = gson.fromJson(jsonData, new TypeToken<List<Information>>(){}.getType());
//        for (Information information:list){
//            //操作
//        }
////        try{
////            JSONArray jsonArray = new JSONArray(jsonData);
////            for(int i = 0; i<jsonArray.length();i++){
////                JSONObject jsonObject = jsonArray.getJSONObject(i);
////                String id = jsonObject.getString("id");
////                String name = jsonObject.getString("name");
////                String version = jsonObject.getString("object");
////            }
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }

//    private void showResponse(final String response){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //将结果显示在界面上
//                responseText.setText(response);
//            }
//        });
//    }

}
