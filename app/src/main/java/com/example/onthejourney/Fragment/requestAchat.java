package com.example.onthejourney.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onthejourney.Adapter.RequestFragmentPagerAdapter;
import com.example.onthejourney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class requestAchat extends Fragment {


    public requestAchat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_achat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RequestFragmentPagerAdapter adapter = new RequestFragmentPagerAdapter(
                getActivity().getSupportFragmentManager()
        );
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.reqViewPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.reqTabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
