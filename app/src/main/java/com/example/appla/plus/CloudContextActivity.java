package com.example.appla.plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CloudContextActivity extends AppCompatActivity implements View.OnClickListener{

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
                break;
            case R.id.kaoshikemu:
                break;
            case R.id.baolubi:
                break;
            case R.id.fulubi:
                break;
            case R.id.tuimianbili:
                break;
            case R.id.jiuyelv:
                break;
            case R.id.fenshuxian:
                break;
            case R.id.kaoshidagang:
                break;
            case R.id.zhuanxueshuo:
                break;
            default:
                break;
        }
    }
}
