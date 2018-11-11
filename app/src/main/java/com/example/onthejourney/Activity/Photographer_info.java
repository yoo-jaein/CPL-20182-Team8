
package com.example.onthejourney.Activity;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.example.onthejourney.Adapter.RecyclerViewAdapter;
import com.example.onthejourney.Data.MyItem;
import com.example.onthejourney.Module.RequestHttpURLConnection;
import com.example.onthejourney.R;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Photographer_info extends AppCompatActivity {

    private ArrayList<String> image_path_list;
    private ImageView imageView;
    private RecyclerView rvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_info);

        MyItem myItem = (MyItem) getIntent().getParcelableExtra("MyItem");
        image_path_list = (ArrayList<String>)getIntent().getStringArrayListExtra("List");
        Log.d("Myitem", myItem.toString());
        Log.d("image_path_list", image_path_list.toString());

        TextView textView = findViewById(R.id.textView2);
        textView.setText(myItem.getTitle());
        imageView = findViewById(R.id.imageView);
        Glide.with(this).load("http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/"+image_path_list.get(0)).into(imageView);

        this.rvSample = (RecyclerView) findViewById(R.id.rvSample);
        this.rvSample.setLayoutManager(new GridLayoutManager(this,3));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, image_path_list);
        this.rvSample.setAdapter(adapter);
    }

}