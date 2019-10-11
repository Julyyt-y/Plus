package com.example.kaoyan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kaoyan.R;
import com.example.kaoyan.View.PersonalItemView;
import com.example.kaoyan.personal_center.Collect;
import com.example.kaoyan.personal_center.Message_center;
import com.example.kaoyan.personal_center.Relation;
import com.example.kaoyan.personal_center.Setting;
import com.example.kaoyan.personal_center.UserInfo;


/**
 * 我的
 */

public class MyTabFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private View view;

    private PersonalItemView message_center,setting,relation,collect;
    private ImageView h_head;
    Button goal_change;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (view == null){

                view = View.inflate(context,R.layout.fragment_tab_me,null);

        }
        initView(view);
        return view;
    }
    private void initView(View view){
        collect = (PersonalItemView) view.findViewById(R.id.collect);
        message_center = (PersonalItemView) view.findViewById(R.id.message_center);
        relation = (PersonalItemView) view.findViewById(R.id.relation);
        setting = (PersonalItemView) view.findViewById(R.id.setting);

        h_head = (ImageView) view.findViewById(R.id.h_back);
        goal_change =(Button) view.findViewById(R.id.goal_change);

        setListener();


    }
    private void setListener() {
        collect.setOnClickListener(this);
        message_center.setOnClickListener(this);
        relation.setOnClickListener(this);
        setting.setOnClickListener(this);
        h_head.setOnClickListener(this);
        goal_change.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.collect:
                Intent collectIntent = new Intent(getActivity(), Collect.class);
                startActivity(collectIntent);
                break;
            case R.id.message_center:
                Intent messageIntent = new Intent(getActivity(), Message_center.class);
                startActivity(messageIntent);
                break;
            case R.id.relation:
                Intent relationntent = new Intent(getActivity(), Relation.class);
                startActivity(relationntent);
                break;
            case R.id.setting:
                Intent settingIntent = new Intent(getActivity(), Setting.class);
                startActivity(settingIntent);
                break;

            case R.id.h_head:
                Intent userInfoIntent = new Intent(getActivity(), UserInfo.class);
                startActivity(userInfoIntent);
                break;
        }

    }
}
