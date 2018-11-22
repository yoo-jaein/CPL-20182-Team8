package com.example.immmy.myapplication.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.immmy.myapplication.Fragment.LikeBuddyFragment;
import com.example.immmy.myapplication.Fragment.LikeFragment;
import com.example.immmy.myapplication.Fragment.LikePhotoFragment;

public class LikeFragmentPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {
    public LikeFragmentPagerAdapter(FragmentManager fm) {
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
