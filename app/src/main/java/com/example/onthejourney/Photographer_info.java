package com.example.onthejourney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Photographer_info extends AppCompatActivity {

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
