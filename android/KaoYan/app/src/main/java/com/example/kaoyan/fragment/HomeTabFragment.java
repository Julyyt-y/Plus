package com.example.kaoyan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoyan.R;
import com.example.kaoyan.search.SearchFragment;


/**
 * 首页
 */

public class HomeTabFragment extends Fragment {

    private Context context;
    private View view;
    SearchFragment searchFragment;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (view == null){
            view = View.inflate(context,R.layout.fragment_search,null);
        }
        return view;
    }
}
