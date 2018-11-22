package com.example.immmy.myapplication.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.immmy.myapplication.Adapter.LikeFragmentPagerAdapter;
import com.example.immmy.myapplication.Fragment.LikeFragment;
import com.example.immmy.myapplication.Fragment.MapFragment;
import com.example.immmy.myapplication.Fragment.MyPageFragment;
import com.example.immmy.myapplication.Fragment.SearchFragment;
import com.example.immmy.myapplication.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class HomeActivity extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // Intent login_select = new Intent(this, LoginSelectActivity.class);
       // startActivity(login_select);

        fragmentManager = getSupportFragmentManager();

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavigationView.setTextVisibility(false);
        bottomNavigationView.enableItemShiftingMode(true);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(true);

        // HomeActivity의 Default Fragment 설정
        if(savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.main_container,new SearchFragment()).commit();
        }

        // BottomNavigationView의 item을 클릭했을 때 Fragment 전환
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                TabLayout mTab = (TabLayout)findViewById(R.id.tabs);
                flag = 0;
                mTab.setVisibility(TabLayout.INVISIBLE);

                switch (item.getItemId()) {
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.map:
                        Intent intent = new Intent(HomeActivity.this,MapsActivity.class);
                        startActivity(intent);
                        flag = 1;
                        fragment = new MapFragment();
                        break;
                    case R.id.like:
                        flag = 2;
                        fragment = new LikeFragment();
                        break;
                    case R.id.mypage:
                        fragment = new MyPageFragment();
                        break;
                }
                if(flag == 1){
                    return true;
                }
                else if(flag == 2){
                    LikeFragmentPagerAdapter mFragmentPagerAdapter = new LikeFragmentPagerAdapter(getSupportFragmentManager());
                    ViewPager mViewPager = (ViewPager)findViewById(R.id.fragmentviewpager);
                    mViewPager.setAdapter(mFragmentPagerAdapter);

                    mTab.setupWithViewPager(mViewPager);
                    mTab.setVisibility(TabLayout.VISIBLE);

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_container, fragment).commit();

                    return true;
                }
                else {
                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_container, fragment).commit();
                    return true;
                }
            }
        });
    }
}