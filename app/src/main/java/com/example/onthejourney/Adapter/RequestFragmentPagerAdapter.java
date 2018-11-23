package com.example.onthejourney.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.onthejourney.Fragment.ChatFragment;
import com.example.onthejourney.Fragment.RequestFragment;

public class RequestFragmentPagerAdapter extends FragmentPagerAdapter {
    public RequestFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "예약 요청";
            case 1:
                return "채팅";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RequestFragment.newInstance();
            case 1:
                return ChatFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
