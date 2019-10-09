package com.example.appla.plus.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appla.plus.R;


/**
 * 首页
 */

public class HomeTabFragment extends Fragment {

    private Context context;
    private View view;
//    private Context mContext;
//    private ListView mListView;
//    private View mHeadView;
//    private View mFloatBarInLvHeader;
//    private View mFloatBar;
//    MyAdapter adapter;


    public Activity mActivity;
    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent; //空的帧布局对象
    public View mRootView; //当前页面的布局文件对象

    public FragmentActivity Activity;





    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mContext = this;
//        mListView = (ListView) findViewById(R.id.lv);
//        // ListView 顶部隐藏的浮动栏
//        mFloatBar = findViewById(R.id.float_bar);
//
//        // ListView 第一个头部控件（效果图中的 色区域）
//        mHeadView = LayoutInflater.from(mContext).inflate(R.layout.home_hidden_bar, mListView, false);
//        mListView.addHeaderView(mHeadView);
//        // ListView 第二个头部控件（浮动栏）
//        mFloatBarInLvHeader = LayoutInflater.from(mContext).inflate(R.layout.home_stick_bar, mListView, false);
//        mListView.addHeaderView(mFloatBarInLvHeader);
//
//        List<String> data = new ArrayList<>(100);
//        for (int i = 0; i < 100; ++i) {
//            data.add(String.valueOf(i));
//        }
//        adapter = new MyAdapter(data, this);
//
//        mListView.setAdapter(adapter);
//        // 监听 ListView 滑动事件
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                /* 判断ListView头部中的浮动栏(mFloatBarInLvHeader)当前是否可见
//                 * 来决定隐藏或显示浮动栏(mFloatBar)*/
//                if (firstVisibleItem >= 1) {
//                    mFloatBar.setVisibility(View.VISIBLE);
//                } else {
//
//                    mFloatBar.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (view == null){
            view = View.inflate(context,R.layout.fragment_tab_home,null);
        }
        return view;
    }



}
