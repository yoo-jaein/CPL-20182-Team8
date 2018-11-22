package com.example.immmy.myapplication.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.immmy.myapplication.Activity.Photographer_info;
import com.example.immmy.myapplication.Adapter.Item_likephotographer_adapter;
import com.example.immmy.myapplication.Adapter.Item_likephotographer_listener;
import com.example.immmy.myapplication.Adapter.RecyclerViewAdapter;
import com.example.immmy.myapplication.Data.Buddy;
import com.example.immmy.myapplication.Data.Customer;
import com.example.immmy.myapplication.Module.RequestHttpURLConnection;
import com.example.immmy.myapplication.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikePhotoFragment extends Fragment {

    public static final Customer customer = new Customer("traveler",
            "hhm",
            "kim");
    private ArrayList<String> favorite_image;

    public LikePhotoFragment() {}
    public static LikePhotoFragment newInstance() {
        LikePhotoFragment fragment = new LikePhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static LikePhotoFragment newInstance(String param1, String param2) {
        LikePhotoFragment fragment = new LikePhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_like_photo, container, false);
        RecyclerView r = (RecyclerView) view.findViewById(R.id.rvLikePhoto);
        r.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;


       // * return inflater.inflate(R.layout.fragment_like_photo, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NetworkTask networkTask = new NetworkTask("favorite_feeds/customer/", "feed_id", new NetworkTask.Listener() {
            @Override
            public void onFinished(@NonNull final ArrayList<String> s) {

                RecyclerView rv = (RecyclerView) getView().findViewById(R.id.rvLikePhoto);
                rv.setLayoutManager(new GridLayoutManager(getContext(),3));
                rv.setAdapter(new RecyclerViewAdapter(getContext(), s));

                // * RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), s);
                // *rv.setAdapter(adapter);

            }
        });
        networkTask.execute();

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
            ArrayList<String> feedId = new ArrayList<>();
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.getJsonText(option1, option2, customer.getCustomer_id());


            return result;
        }

        protected void onPostExecute(ArrayList<String> s) {
            listener.onFinished(s);

        }
    }
}