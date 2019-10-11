package com.example.kaoyan.tagcloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kaoyan.R;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private int resourceId;
    public ItemAdapter( Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView titleView = view.findViewById(R.id.title);
        TextView titleItemView = view.findViewById(R.id.title_item);
        titleView.setText(item.getTitle());
        titleItemView.setText(item.getTitleItem());
        return super.getView(position, convertView, parent);
    }
}
