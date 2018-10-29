package com.example.onthejourney;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotographerListViewAdapter extends BaseAdapter {
    private ArrayList<MyItem> listViewItemList = new ArrayList<MyItem>();

    public PhotographerListViewAdapter(ArrayList<MyItem> arrayList){
        listViewItemList = arrayList;
    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photographer,parent,false);
        }

        //ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView titleTextView = (TextView)convertView.findViewById(R.id.textView);

        MyItem myItem = listViewItemList.get(position);

        //iconImageView.setImageDrawable(myItem.getImage);
        titleTextView.setText(myItem.getTitle());

        return convertView;
    }

}
