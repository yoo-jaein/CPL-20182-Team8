
package com.example.onthejourney.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onthejourney.Adapter.RecyclerViewAdapter;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.CheckList;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Dialog.CheckListDialog;
import com.example.onthejourney.Module.HttpAsyncTask;
import com.example.onthejourney.Module.MyCallBack;
import com.example.onthejourney.Module.ResultBody;
import com.example.onthejourney.R;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Photographer_info extends AppCompatActivity {

    Context context;
    private ImageView imageView;
    private RecyclerView rvSample;
    private boolean like = false;
    private Button request;
    Buddy buddy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_info);

        context = this;
        buddy = (Buddy) getIntent().getParcelableExtra("Buddy");
        final Customer me = (Customer)getIntent().getParcelableExtra("Customer");
        ArrayList<String> image_path_list = (ArrayList<String>)getIntent().getStringArrayListExtra("List");

        Log.d("Customer", me.toString());
        Log.d("image_path_list", image_path_list.toString());

        request = findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckListDialog dialog = new CheckListDialog(v.getContext());
                dialog.setDialogListener(new CheckListDialog.MyDialogListener() {
                    @Override
                    public void onPositiveClicked(CheckList checkList) {
                        Log.d("checklist",checkList.toString());
                        checkList.setState("예약중");
                        checkList.setBuddy_id(buddy.getBuddy_id());
                        checkList.setCustomer_id(me.getCustomer_id());
                        JSONObject jsonObject = checkList.getJsonObject();
                        new HttpAsyncTask("POST", "checkLists", jsonObject, null, new TypeToken<ResultBody<CheckList>>() {
                        }.getType(),
                                new MyCallBack() {
                                    @Override
                                    public void doTask(Object resultBody) {
                                        ResultBody<CheckList> result = (ResultBody<CheckList>) resultBody;
                                    }
                                })
                                .execute();
                        Toast.makeText(context, "작업 제안이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeClicked() {
                        Toast.makeText(context, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("MyDialogListener","onNegativeClicked");
                    }
                });
                dialog.show();
            }
        });

        TextView textView = findViewById(R.id.textView2);
        textView.setText(buddy.getTitle());
        CheckBox likeText = findViewById(R.id.likeInInfo);
        if(buddy.getLikeFlag() == 1){
            like = true;
            likeText.setChecked(like);
        }
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
        imageView = findViewById(R.id.imageView);
        Glide.with(this).load("http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/"+image_path_list.get(0)).into(imageView);

        this.rvSample = (RecyclerView) findViewById(R.id.rvSample);
        this.rvSample.setLayoutManager(new GridLayoutManager(this,3));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, image_path_list,me);
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
    public void reviewClick(View v){
        Intent i = new Intent(Photographer_info.this, ReviewActivity.class);
        i.putExtra("Buddy",buddy);
        startActivity(i);
    }
}