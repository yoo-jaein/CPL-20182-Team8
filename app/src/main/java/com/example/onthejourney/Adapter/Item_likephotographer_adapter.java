package com.example.onthejourney.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class Item_likephotographer_adapter extends RecyclerView.Adapter<Item_likephotographer_adapter.ItemViewHolder> {
    ArrayList<ArrayList<String>> image_path_list;
    private String url = "http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/";
    private ArrayList<Buddy> buddy;
    Context context;
    public Item_likephotographer_adapter(ArrayList<Buddy> buddy, ArrayList<ArrayList<String>> image_path_list) {
        this.buddy = buddy;
        this.image_path_list = image_path_list;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_likephotographer_recycler_view, viewGroup, false);
        context = viewGroup.getContext();
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Glide.with(context).load(url + image_path_list.get(i).get(0)).into(itemViewHolder.imageView);
        itemViewHolder.textView.setText(buddy.get(i).getBuddy_id());
    }


    @Override
    public int getItemCount() {
        return buddy.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_likephotographer_image);
            textView = (TextView) itemView.findViewById(R.id.item_likephotographer_buddyid);
        }
    }
}
