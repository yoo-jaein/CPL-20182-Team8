
package com.example.onthejourney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onthejourney.Adapter.RecyclerViewAdapter;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class Photographer_info extends AppCompatActivity {

    private ImageView imageView;
    private RecyclerView rvSample;
    private boolean like = false;
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
        CheckBox likeText = findViewById(R.id.likeInInfo);
        if(buddy.getLikeFlag() == 1){
            likeText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if(like == false) like=true; //true이면 선호작가에 추가
                        else like=false;
                    }
                    return false;
                }
            });
        }
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