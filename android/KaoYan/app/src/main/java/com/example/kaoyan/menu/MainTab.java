package com.example.kaoyan.menu;


import com.example.kaoyan.R;
import com.example.kaoyan.fragment.HomeTabFragment;
import com.example.kaoyan.fragment.MessageTabFragment;
import com.example.kaoyan.fragment.MyTabFragment;
import com.example.kaoyan.fragment.SchoolTabFragment;

public enum MainTab {
    Home(0,"首页",R.drawable.selector_tab_home, HomeTabFragment.class),
    Message(1,"信息",R.drawable.selector_tab_message,MessageTabFragment.class),
    School(2,"院校",R.drawable.selsctor_tab_school,SchoolTabFragment.class),
    My(3,"我的",R.drawable.selector_tab_me,MyTabFragment.class);


    private int i;
    private String name;
    private int icon;
    private Class<?> cla;

    MainTab(int i, String name, int icon, Class<?> cla){
        this.i = i;
        this.name = name;
        this.icon =icon;
        this.cla = cla;
    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<?> getCla() {
        return cla;
    }

    public void setCla(Class<?> cla) {
        this.cla = cla;
    }

}
