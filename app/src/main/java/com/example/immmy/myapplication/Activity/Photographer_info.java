
package com.example.immmy.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.immmy.myapplication.Adapter.RecyclerViewAdapter;
import com.example.immmy.myapplication.Data.Buddy;
import com.example.immmy.myapplication.R;

import java.util.ArrayList;

public class Photographer_info extends AppCompatActivity {

    private ImageView imageView;
    private RecyclerView rvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_info);

        Buddy buddy = (Buddy) getIntent().getParcelableExtra("Buddy");
        ArrayList<String> image_path_list = (ArrayList<String>)getIntent().getStringArrayListExtra("List");

        Log.d("Myitem", buddy.toString());
        Log.d("image_path_list", image_path_list.toString());

        TextView textView = findViewById(R.id.textView2);
        textView.setText(buddy.getTitle());
        imageView = findViewById(R.id.imageView);
        Glide.with(this).load("http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/"+image_path_list.get(0)).into(imageView);

        this.rvSample = (RecyclerView) findViewById(R.id.rvSample);
        this.rvSample.setLayoutManager(new GridLayoutManager(this,3));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, image_path_list);
        this.rvSample.setAdapter(adapter);
    }

    public void noticeClick(View v){
        Intent i = new Intent(Photographer_info.this, Notice.class);
        startActivity(i);
    }
    public void introduceClick(View v){
        Intent i = new Intent(Photographer_info.this, IntroducePrice.class);
        startActivity(i);
    }
    public void scheduleClick(View v){
        Intent i = new Intent(Photographer_info.this, Schedule.class);
        startActivity(i);
    }
}