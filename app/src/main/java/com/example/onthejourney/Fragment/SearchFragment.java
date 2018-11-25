package com.example.onthejourney.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Module.HttpAsyncTask;
import com.example.onthejourney.Module.MyCallBack;
import com.example.onthejourney.Module.ResultBody;
import com.example.onthejourney.R;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;


public class SearchFragment extends Fragment {
    private EditText tv;
    private Button button;
    public SearchFragment() {}

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tv = (EditText) view.findViewById(R.id.editTextInsearch);
        button = (Button)view.findViewById(R.id.buttonInsearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv.getText().toString();
                final TextView myText = v.findViewById(R.id.textInsearch);


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("customer_id", text);
                    jsonObject.put("name", "100");
                    jsonObject.put("nickname", "hello");
                }catch (Exception e){
                    e.printStackTrace();
                }

                new HttpAsyncTask("POST", "customers", jsonObject, null, new TypeToken<ResultBody<Customer>>() {
                }.getType(),
                        new MyCallBack() {
                            @Override
                            public void doTask(Object resultBody) {
                                ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
                                myText.setText(result.getDatas().get(0).getName());
                            }
                        }).execute();

            }
        });
        tv.setHint("Search");
        return view;
    }
}