package com.example.kaoyan.tagcloud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kaoyan.R;
import com.moxun.tagcloudlib.view.TagsAdapter;

public class VectorTagsAdapter extends TagsAdapter {

    int count;  //学校的数量

    //返回tag数量
    @Override
    public int getCount() {
        return 20;
    }

    //返回每个tag的实例
    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.tag_item_vector, parent, false);
    }

    //返回tag数据
    @Override
    public Object getItem(int position) {
        return null;
    }

    //针对每个tag返回一个权重值，该值与ThemeColor和Tag初始大小有关；
    //一个简单的权重值生成方式是对一个数N取余或使用随机数
    @Override
    public int getPopularity(int position) {
        Log.e("","Popularity" + position % 5);
        return position % 5;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ImageView imageView = view.findViewById(R.id.vector_img);
        //取上层非交集部分与下层交集部分,这里的上层和上层是指调用canvas的先后顺序，
        //先调用canvas绘制的就是下层图像，后调用的就是上层图像，
        // canvas是一层一层绘制的，后绘制的会覆盖在先绘制的上层
//        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(themeColor,
//                PorterDuff.Mode.SRC_ATOP);
//        if (imageView == null) {
//            return;
//        }
//        imageView.getDrawable().setColorFilter(porterDuffColorFilter);
    }
}
