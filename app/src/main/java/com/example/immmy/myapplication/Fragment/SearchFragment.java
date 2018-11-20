package com.example.immmy.myapplication.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.immmy.myapplication.Adapter.RecyclerViewAdapter;
import com.example.immmy.myapplication.R;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class SearchFragment extends Fragment {
    private ArrayList<String> image;

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

        //hideSoftKeyboard();
        return view;
    }

    // 키보드 숨기기 - 아직 구현x
    private void hideSoftKeyboard(){
        if (getActivity().getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // 최신 사진 불러오기 - 아직 구현x
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = new ArrayList<>();

        RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.rvPhoto);
        rv.setLayoutManager(new GridLayoutManager(getContext(),3));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), image);
        rv.setAdapter(adapter);
    }
}