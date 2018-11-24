package com.example.onthejourney.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.onthejourney.Fragment.LikeBuddyFragment;
import com.example.onthejourney.Fragment.LikePhotoFragment;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LikeBuddyFragment.newInstance();
            case 1:
                return LikePhotoFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "사진작가";
            case 1:
                return "사진";
            default:
                return null;
        }
    }
}
