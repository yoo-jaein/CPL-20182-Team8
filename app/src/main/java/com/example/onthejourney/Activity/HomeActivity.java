package com.example.onthejourney.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.onthejourney.Adapter.LikeFragmentPagerAdapter;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Fragment.LikeBuddyFragment;
import com.example.onthejourney.Fragment.LikeFragment;
import com.example.onthejourney.Fragment.MapFragment;
import com.example.onthejourney.Fragment.MyPageFragment;
import com.example.onthejourney.Fragment.SearchFragment;
import com.example.onthejourney.Fragment.requestAchat;
import com.example.onthejourney.R;
import com.google.android.gms.maps.model.LatLng;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class HomeActivity extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private int flag = 0;
    Buddy buddy = null;
    Customer Customer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buddy = new Buddy(32.7, 160.5, "hiroo~");
        Customer = (Customer) getIntent().getParcelableExtra("Customer");
        // Intent login_select = new Intent(this, LoginSelectActivity.class);
        // startActivity(login_select);


        fragmentManager = getSupportFragmentManager();

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        bottomNavigationView.setTextVisibility(false);
        bottomNavigationView.enableItemShiftingMode(true);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(true);

        // HomeActivity의 Default Fragment 설정
        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.main_container, new SearchFragment()).commit();
        }

        // BottomNavigationView의 item을 클릭했을 때 Fragment 전환
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                flag = 0;

                switch (item.getItemId()) {
                    case R.id.search:
                        fragment = new requestAchat();
                        Bundle args = new Bundle();
                        args.putParcelable("Buddy", buddy);
                        fragment.setArguments(args);
                        break;
                    case R.id.map:
                        Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                        intent.putExtra("Customer", Customer);
                        startActivity(intent);
                        flag = 1;
                        fragment = new MapFragment();
                        break;
                    case R.id.like:
                        fragment = new LikeFragment();
                        Bundle arg = new Bundle();
                        arg.putParcelable("Customer", Customer);
                        fragment.setArguments(arg);
                        break;
                    case R.id.mypage:
                        fragment = new MyPageFragment();
                        Bundle args1 = new Bundle();
                        args1.putParcelable("Customer", Customer);
                        Log.d("Customer", Customer.toString());
                        fragment.setArguments(args1);
                        break;
                }
                if (flag == 1) {
                    return true;
                } else {

                    while(fragmentManager.isStateSaved())
                        fragmentManager.popBackStackImmediate();

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();

                    transaction.replace(R.id.main_container, fragment).commit();
                    return true;
                }
            }
        });
    }
}