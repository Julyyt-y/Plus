package com.example.appla.plus.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

 import com.example.appla.plus.R;

/**
 * 学校
 */

public class SchoolTabFragment extends Fragment {

    private Context context;
    private View view;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        if (view == null){

                view = View.inflate(context,R.layout.fragment_tab_school,null);

        }return view;
    }
}
