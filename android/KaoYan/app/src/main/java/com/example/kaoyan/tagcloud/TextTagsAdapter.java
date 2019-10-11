package com.example.kaoyan.tagcloud;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaoyan.CloudContextActivity;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextTagsAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();
    TextView textView;
    private Context context;
    private String schoolName = "中北大学";
    private ArrayList<String> schoolNames=new ArrayList<>();

    public TextTagsAdapter(Context context, @NonNull String... data) {
        this.context = context;
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    //返回tag数量
    @Override
    public int getCount() {
        return dataSet.size();
    }

    //返回每个tag实例
    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(schoolName);     //tv.setText("No." + position);
        tv.setTextSize(12);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CloudContextActivity.class));
            }
        });
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    //返回tag数据
    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    //针对每个Tag返回一个权重值，该值与ThemeColor和Tag初始大小有关；
    //一个简单的权重值生成方式是对一个数N取余或使用随机数
    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
//        view.setBackgroundColor(themeColor);  //设置每个标签的背景色
    }
}
