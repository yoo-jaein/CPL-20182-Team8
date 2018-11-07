package com.example.onthejourney.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onthejourney.Activity.MapsActivity;
import com.example.onthejourney.Data.MyItem;
import com.example.onthejourney.Module.GetImage;
import com.example.onthejourney.Module.ServerModule;
import com.example.onthejourney.R;

import java.io.File;
import java.util.ArrayList;

public class PhotographerListViewAdapter extends BaseAdapter {
    private ArrayList<MyItem> listViewItemList = new ArrayList<MyItem>();
    String imageUrl = "";
    Bitmap bitmap = null;
    public PhotographerListViewAdapter(ArrayList<MyItem> arrayList) {
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photographer, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView);
        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);


        MyItem myItem = listViewItemList.get(position);

        //iconImageView.setImageDrawable(myItem.getImage);
        titleTextView.setText(myItem.getTitle());
        new GetImage().execute(bitmap, imageUrl);
        imageView1.setImageBitmap(bitmap);
        imageView2.setImageBitmap(bitmap);
        imageView3.setImageBitmap(bitmap);

        return convertView;
    }
}