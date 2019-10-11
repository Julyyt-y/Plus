package com.example.kaoyan.tagcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kaoyan.R;
import com.moxun.tagcloudlib.view.TagCloudView;

public class CloudActivity extends AppCompatActivity {

    private TagCloudView tagCloudView;
    private TextTagsAdapter textTagsAdapter;
//    private ViewTagsAdapter viewTagsAdapter;
    private VectorTagsAdapter vectorTagsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tagCloudView = findViewById(R.id.tag_cloud);

        textTagsAdapter = new TextTagsAdapter(this, new String[20]);
//        viewTagsAdapter = new ViewTagsAdapter();
        vectorTagsAdapter = new VectorTagsAdapter();

        tagCloudView.setAdapter(textTagsAdapter);

    }
}
