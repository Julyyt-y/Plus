package com.example.kaoyan;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kaoyan.menu.MainTab;


public class BottomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_layout);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initFragmentTabHost();
    }

    private void initFragmentTabHost() {
        //初始化tabHost
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        //将tabHost和FragmentLayout关联
        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.fl_content);

        //去掉分割线
        if (Build.VERSION.SDK_INT > 10) {
            tabHost.getTabWidget().setShowDividers(0);
        }

        //添加tab和其对应的fragment
        MainTab[] tabs = MainTab.values();
        for (int i = 0; i < tabs.length; i++) {
            MainTab mainTabs = tabs[i];
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mainTabs.getName());

            View indicator = View.inflate(getApplicationContext(), R.layout.bottom_tab, null);
            TextView bottom_tab_indicator = (TextView) indicator.findViewById(R.id.bottom_tab_indicator);
            Drawable drawable = getApplicationContext().getResources().getDrawable(mainTabs.getIcon());

            bottom_tab_indicator.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            bottom_tab_indicator.setText(mainTabs.getName());
            tabSpec.setIndicator(indicator);
            tabHost.addTab(tabSpec, mainTabs.getCla(), null);
        }

    }
}
