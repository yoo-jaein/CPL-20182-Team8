package com.example.onthejourney.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.onthejourney.Activity.HomeActivity;
import com.example.onthejourney.Activity.LoginActivity;
import com.example.onthejourney.Adapter.RequestAdapter;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.CheckList;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Module.HttpAsyncTask;
import com.example.onthejourney.Module.MyCallBack;
import com.example.onthejourney.Module.ResultBody;
import com.example.onthejourney.R;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    public static RequestFragment newInstance(Buddy buddy){
        Bundle args = new Bundle();
        args.putParcelable("Buddy",buddy);
        RequestFragment fragment = new RequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Buddy buddy = (Buddy)getArguments().getParcelable("Buddy");
        final ArrayList<CheckList> checkLists = new ArrayList<>();
        new HttpAsyncTask("GET", "checklists/buddy/"+buddy.getBuddy_id(), null, null, new TypeToken<ResultBody<CheckList>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<CheckList> result = (ResultBody<CheckList>) resultBody;

                        for(int i=0;i<result.getDatas().size();i++){
                            checkLists.add((CheckList)result.getDatas().get(i));
                        }

                        ListView listView = view.findViewById(R.id.listView_request);
                        RequestAdapter adapter = new RequestAdapter(checkLists);
                        listView.setAdapter(adapter);
                    }
                }).execute();
    }
}
