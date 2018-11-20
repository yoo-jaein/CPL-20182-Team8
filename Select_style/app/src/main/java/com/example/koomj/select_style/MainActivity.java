package com.example.koomj.select_style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text_name;
    Button btn_signup;
    CheckBox cb_cond_trav, cb_cond_fam, cb_cond_pet, cb_cond_baby, cb_cond_daily, cb_cond_wedding, cb_cond_edit; // cb_cond_flo, cb_cond_anni,
    String nickname;
    //int travel_cnt = 0, family_cnt = 0, pet_cnt = 0, baby_cnt = 0, daily_cnt = 0, wedding_cnt = 0, edit_cnt = 0; //flower_cnt = 0, anni_cnt = 0,
    boolean travel=false, family=false, pet=false, baby=false, daily=false, wedding=false, edit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_name = (EditText) findViewById(R.id.edit_nickname);
        final String tmp_name = text_name.getText().toString();
        cb_cond_trav = (CheckBox) findViewById(R.id.cond_travel);
        cb_cond_fam = (CheckBox) findViewById(R.id.cond_family);
        cb_cond_pet = (CheckBox) findViewById(R.id.cond_pet);
        cb_cond_baby = (CheckBox) findViewById(R.id.cond_baby);
        cb_cond_daily = (CheckBox) findViewById(R.id.cond_daily);
        cb_cond_wedding = (CheckBox) findViewById(R.id.cond_wedding);
        cb_cond_edit = (CheckBox) findViewById(R.id.cond_edit);

        btn_signup = (Button) findViewById(R.id.sign_up);

        cb_cond_fam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(family == false) family=true; //true이면 hashtag에 추가
                    else family=false;
                }
                return false;
            }
        });

        cb_cond_trav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(travel == false) travel=true; //true이면 hashtag에 추가
                    else travel=false;
                }
                return false;
            }
        });

        cb_cond_pet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(pet == false) pet=true; //true이면 hashtag에 추가
                    else pet=false;
                }
                return false;
            }
        });

        cb_cond_baby.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(baby == false) baby=true; //true이면 hashtag에 추가
                    else baby=false;
                }
                return false;
            }
        });

        cb_cond_daily.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(daily == false) daily=true; //true이면 hashtag에 추가
                    else daily=false;
                }
                return false;
            }
        });

        cb_cond_wedding.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(wedding == false) wedding=true; //true이면 hashtag에 추가
                    else wedding=false;
                }
                return false;
            }
        });

        cb_cond_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(edit == false) edit=true; //true이면 hashtag에 추가
                    else edit=false;
                }
                return false;
            }
        });


        btn_signup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //서버에 POST
                    nickname = text_name.getText().toString();
                    btn_signup.setBackground(getDrawable(R.drawable.signup_o));
                    if(nickname.length() == 0) {
                        Toast.makeText(MainActivity.this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                        btn_signup.setBackground(getDrawable(R.drawable.signup_x));
                    }
                    else {
                        //서버에 user의 id, pw, nickname, hashtag POST
                    }
                }
                return false;
            }
        });
    }
}
