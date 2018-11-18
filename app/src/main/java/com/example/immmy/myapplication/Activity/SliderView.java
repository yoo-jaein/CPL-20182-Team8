package com.example.immmy.myapplication.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.immmy.myapplication.Adapter.SliderAdapter;
import com.example.immmy.myapplication.R;

import java.util.ArrayList;

public class SliderView extends AppCompatActivity {

    SliderAdapter adapter;
    int position;
    ViewPager viewPager;
    ArrayList<String> image_path_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_view);

        image_path_list = getIntent().getStringArrayListExtra("List");
        position = getIntent().getIntExtra("position",0);
        viewPager = (ViewPager)findViewById(R.id.view);
        adapter = new SliderAdapter(this, image_path_list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }
}
