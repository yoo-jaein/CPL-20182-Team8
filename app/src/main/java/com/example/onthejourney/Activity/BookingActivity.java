package com.example.onthejourney.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onthejourney.Adapter.RequestAdapter;
import com.example.onthejourney.Data.CheckList;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Dialog.PhotographerCheckListDialog;
import com.example.onthejourney.Fragment.MyPageFragment;
import com.example.onthejourney.Module.HttpAsyncTask;
import com.example.onthejourney.Module.MyCallBack;
import com.example.onthejourney.Module.ResultBody;
import com.example.onthejourney.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {
    private ListView chat_list;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

         customer = getIntent().getParcelableExtra("Customer");
        Log.d("buddyinBookingActivity", customer.toString());



        chat_list = findViewById(R.id.chat_listInbook);

        showChatList();

    }


    private void showChatList() {
        // 리스트 어댑터 생성 및 세팅

        final ArrayList<CheckList> checkLists = new ArrayList<>();
        new HttpAsyncTask("GET", "checklists/customer/" + customer.getCustomer_id(), null, null, new TypeToken<ResultBody<CheckList>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<CheckList> result = (ResultBody<CheckList>) resultBody;

                        for (int i = 0; i < result.getDatas().size(); i++) {
                            if (result.getDatas().get(i).getState().equals("진행중")) {
                                checkLists.add((CheckList) result.getDatas().get(i));
                                Log.d("진행중",checkLists.get(0).toString());
                            }
                        }


                        RequestAdapter adapter = new RequestAdapter(checkLists);
                        chat_list.setAdapter(adapter);
                        chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(BookingActivity.this, ChatActivity.class);
                                intent.putExtra("Customer", customer);
                                Log.d("Customer", customer.toString());
                                intent.putExtra("chatName", chat_list.getItemAtPosition(position).toString());
                                Log.d("chatName", chat_list.getItemAtPosition(position).toString());
                                intent.putExtra("userName", customer.getCustomer_id());
                                startActivity(intent);
                            }
                        });
                    }
                }).execute();




    }

}






