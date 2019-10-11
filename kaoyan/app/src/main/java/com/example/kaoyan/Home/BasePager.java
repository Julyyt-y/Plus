package com.example.kaoyan.Home;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kaoyan.R;

public class BasePager {

    public Activity mActivity;
    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent; //空的帧布局对象
    public View mRootView; //当前页面的布局文件对象

    public ImageButton btnPhoto;// 组图切换按钮

    public BasePager(Activity activity){
        mActivity = activity;
        System.out.println("BasePager被创建！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        mRootView = initView();
    }

    /**
     * 初始化布局
     * @return
     */
    public View initView(){
        View view = View.inflate(mActivity, R.layout.fragment_tab_home, null);
        tvTitle = (TextView)view.findViewById(R.id.tv_title);
        btnMenu = (ImageButton)view.findViewById(R.id.btn_menu);
        btnPhoto = (ImageButton)view.findViewById(R.id.btn_photo);
        flContent = (FrameLayout)view.findViewById(R.id.fl_content);


        return view;
    }

    /**
     * 打开或者关闭侧边栏
     */


    /**
     * 初始化数据
     */
    public void initData(){

    }


}
