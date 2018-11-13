package com.example.immmy.myapplication.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.immmy.myapplication.R;

public class LikePhotoFragment extends Fragment {

    public LikePhotoFragment() {}

    public static LikePhotoFragment newInstance(String param1, String param2) {
        LikePhotoFragment fragment = new LikePhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like_photo, container, false);

        return view;
    }
}