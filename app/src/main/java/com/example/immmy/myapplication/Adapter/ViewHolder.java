package com.example.immmy.myapplication.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.immmy.myapplication.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = (ImageView)itemView.findViewById(R.id.recyclerImageView);
    }
}
