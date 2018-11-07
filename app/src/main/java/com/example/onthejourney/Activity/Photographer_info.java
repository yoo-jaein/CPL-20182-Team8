package com.example.onthejourney.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.onthejourney.Data.MyItem;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class Photographer_info extends AppCompatActivity {

    private ArrayList<Bitmap> bitmapArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_info);

        MyItem myItem = (MyItem)getIntent().getParcelableExtra("MyItem");
        System.out.println(myItem);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(myItem.getTitle());
    }
}
