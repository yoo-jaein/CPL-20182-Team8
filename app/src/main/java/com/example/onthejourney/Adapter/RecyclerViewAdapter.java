package com.example.onthejourney.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    String url = "http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/";
    private ArrayList<String> image_path_arr;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.image_path_arr = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewimage, parent, false);
        return new ViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(context).load(url+image_path_arr.get(position)).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return image_path_arr.size();
    }
}
