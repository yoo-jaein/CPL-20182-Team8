package com.example.onthejourney.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onthejourney.Activity.Photographer_info;
import com.example.onthejourney.Adapter.Item_likephotographer_adapter;
import com.example.onthejourney.Adapter.Item_likephotographer_listener;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Module.RequestHttpURLConnection;
import com.example.onthejourney.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LikeBuddyFragment extends Fragment {

    public  ArrayList<Buddy> buddies = new ArrayList<Buddy>();


    public static Customer customer = null;
    private ArrayList<String> favorite_buddy;

    public LikeBuddyFragment() {}
    public static LikeBuddyFragment newInstance(Customer customer) {
        LikeBuddyFragment fragment = new LikeBuddyFragment();
        Bundle args = new Bundle();
        args.putParcelable("Customer",customer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_like_buddy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customer = (Customer)getArguments().get("Customer");


        favorite_buddy = new ArrayList<>();
        favorite_buddy.add("hihi");
        favorite_buddy.add("hiroo~");

        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);

        Buddy buddy1 = new Buddy(DEFAULT_LOCATION.latitude, DEFAULT_LOCATION.longitude);
        buddy1.setBuddy_id("hihi");

        Buddy buddy2 = new Buddy(DEFAULT_LOCATION.latitude, DEFAULT_LOCATION.longitude);
        buddy2.setBuddy_id("hiroo~");

        buddies.add(buddy1);
        buddies.add(buddy2);


        NetworkTask networkTask = new NetworkTask("favorite_buddies/customer/", "buddy_id", new NetworkTask.Listener() {
            @Override
            public void onFinished(final ArrayList<ArrayList<String>> s) {

                Log.d("buddies in onfinished",buddies.toString());

                RecyclerView rv = getActivity().findViewById(R.id.item_likephoto_recyclerView);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rv.setLayoutManager(layoutManager);
                rv.setHasFixedSize(true);


                Log.d("image_path_list",s.toString());

                Item_likephotographer_adapter adapter = new Item_likephotographer_adapter(buddies, s);
                rv.addOnItemTouchListener(
                        new Item_likephotographer_listener(getContext(), rv, new Item_likephotographer_listener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), Photographer_info.class);
                                intent.putExtra("Customer",customer);
                                intent.putExtra("Buddy", buddies.get(position));
                                intent.putStringArrayListExtra("List", s.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        })
                );
                rv.setAdapter(adapter);


            }
        });
        networkTask.execute();


    }

    public static class NetworkTask extends AsyncTask<Void, Void, ArrayList<ArrayList<String>>> {

        private String option1, option2;
        private Listener listener;

        public interface Listener {
            void onFinished(ArrayList<ArrayList<String>> s);
        }

        public NetworkTask(String option1, String option2, Listener listener) {
            this.option1 = option1;
            this.option2 = option2;
            this.listener = listener;
        }

        @Override
        protected ArrayList<ArrayList<String>> doInBackground(Void... voids) {
            ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
            ArrayList<String> buddy;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            buddy = requestHttpURLConnection.getJsonText(option1, option2, customer.getCustomer_id());
            Log.d("InNetTask",buddy.toString());
//            for(int i=0;i<buddy.size(); i++){
//                requestHttpURLConnection = new RequestHttpURLConnection();
//                Buddy buddy1;
//                buddy1 = requestHttpURLConnection.getBuddy("buddies", buddy.get(i));
//                Log.d("buddy1",buddy1.toString());
//                buddies.add(buddy1);
//                Log.d("buddies",buddies.get(i).toString());
//            }
            for (int i = 0; i < buddy.size(); i++) {
                requestHttpURLConnection = new RequestHttpURLConnection();
                ArrayList<String> request = requestHttpURLConnection.getJsonText("feed_items", "image_path", buddy.get(i));
                result.add(request);
                Log.d("DoinBackground", "result : " + result);

            }

            return result;
        }

        protected void onPostExecute(ArrayList<ArrayList<String>> s) {
            listener.onFinished(s);

        }
    }
}