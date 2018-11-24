package com.example.onthejourney.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Module.HttpAsyncTask;
import com.example.onthejourney.Module.MyCallBack;
import com.example.onthejourney.Module.ResultBody;
import com.example.onthejourney.R;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Customer customer = new Customer("hihi","hhm","jun");
//        new HttpAsyncTask("POST", "customers", customer.getJsonObject(), null, new TypeToken<ResultBody<Customer>>() {
//        }.getType(),
//                new MyCallBack() {
//                    @Override
//                    public void doTask(Object resultBody) {
//                        ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
//                        Customer customer = result.getDatas().get(0);
//                        Intent intent = new Intent(LoginActivity.this ,HomeActivity.class);
//                        intent.putExtra("Customer",customer);
//                        startActivity(intent);
//
//                    }
//                }).execute();
        new HttpAsyncTask("GET", "customers/hhm", null, null, new TypeToken<ResultBody<Customer>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
                        Customer customer = result.getDatas().get(0);
                        Intent intent = new Intent(LoginActivity.this ,HomeActivity.class);
                        intent.putExtra("Customer",customer);
                        startActivity(intent);

                    }
                }).execute();

    }

}
