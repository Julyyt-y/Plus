package com.example.kaoyan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoyan.R;
import com.example.kaoyan.tagcloud.TextTagsAdapter;
import com.example.kaoyan.tagcloud.VectorTagsAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

//import com.example.appla.plus.tagcloud.ViewTagsAdapter;

/**
 * 学校
 */

public class SchoolTabFragment extends Fragment {

    private View root;

    private TagCloudView tagCloudView;
    private TextTagsAdapter textTagsAdapter;
//    private ViewTagsAdapter viewTagsAdapter;
    private VectorTagsAdapter vectorTagsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_tab_school, container, false);
        init();
        return root;
    }

    private void init() {
        tagCloudView = root.findViewById(R.id.fragment_tagcloud);


        textTagsAdapter = new TextTagsAdapter(root.getContext(), new String[20]);
//        viewTagsAdapter = new ViewTagsAdapter();
        vectorTagsAdapter = new VectorTagsAdapter();
        tagCloudView.setAdapter(textTagsAdapter);

    }

    //onCreate类似于Activity.onCreate，在其中可初始化除了view之外的一切
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
