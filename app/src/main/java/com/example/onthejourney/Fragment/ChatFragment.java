package com.example.onthejourney.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.onthejourney.Activity.ChatActivity;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.ChatDTO;
import com.example.onthejourney.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private ListView chat_list;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public static ChatFragment newInstance(Buddy buddy){
        Bundle args = new Bundle();
        args.putParcelable("Buddy",buddy);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Buddy buddy = (Buddy)getArguments().get("Buddy");
        Log.d("buddy in chatFragment",buddy.toString());
        chat_list = view.findViewById(R.id.chat_list);
        chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("chatName",chat_list.getItemAtPosition(position).toString() );
                Log.d("chatName",chat_list.getItemAtPosition(position).toString());
                intent.putExtra("userName", buddy.getBuddy_id());
                startActivity(intent);
            }
        });
        showChatList();
    }
    private void showChatList() {
        // 리스트 어댑터 생성 및 세팅
        final ArrayAdapter<String> adapter

                = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);
        chat_list.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("CHAT").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                adapter.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
