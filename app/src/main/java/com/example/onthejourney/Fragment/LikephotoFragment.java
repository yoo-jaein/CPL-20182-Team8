package com.example.onthejourney.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onthejourney.Activity.Photographer_info;
import com.example.onthejourney.Adapter.Item_likephotographer_adapter;
import com.example.onthejourney.Adapter.Item_likephotographer_listener;
import com.example.onthejourney.Adapter.RecyclerViewAdapter;
import com.example.onthejourney.Data.Buddy;
import com.example.onthejourney.Data.Customer;
import com.example.onthejourney.Module.RequestHttpURLConnection;
import com.example.onthejourney.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikephotoFragment extends android.support.v4.app.Fragment {


    public static final Customer customer = new Customer("traveler",
            "abcdefg123",
            "kim");
    private ArrayList<String> favorite_image;

    public static LikephotoFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();

        LikephotoFragment fragment = new LikephotoFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likephoto, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favorite_image = new ArrayList<>();
        favorite_image.add("images/building.png");
        favorite_image.add("images/iot.png");
        favorite_image.add("images/iot2.png");
        favorite_image.add("images/baby.jpg");
        favorite_image.add("images/fome.jpeg");
        customer.setFavorite_feed_id_list(favorite_image);



//        NetworkTask networkTask = new NetworkTask("customers", "favorite_feed_item_id_list", new NetworkTask.Listener() {
//            @Override
//            public void onFinished(final ArrayList<String> s) {

                RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.rvLikePhoto);
                rv.setLayoutManager(new GridLayoutManager(getContext(),3));

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), favorite_image);
                rv.setAdapter(adapter);


//            }
//        });
//        networkTask.execute();


    }

    public static class NetworkTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private String option1, option2;
        private Listener listener;

        public interface Listener {
            void onFinished(ArrayList<String> s);
        }

        public NetworkTask(String option1, String option2, Listener listener) {
            this.option1 = option1;
            this.option2 = option2;
            this.listener = listener;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> result = new ArrayList<String>();
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.getJsonText(option1, option2, customer.getCustomer_id());

            return result;
        }

        protected void onPostExecute(ArrayList<String> s) {
            listener.onFinished(s);

        }
    }
}
