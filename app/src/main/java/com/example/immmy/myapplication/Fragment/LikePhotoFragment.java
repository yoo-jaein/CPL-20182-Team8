package com.example.immmy.myapplication.Fragment;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.immmy.myapplication.Adapter.RecyclerViewAdapter;
import com.example.immmy.myapplication.Data.Customer;
import com.example.immmy.myapplication.Module.RequestHttpURLConnection;
import com.example.immmy.myapplication.R;

import java.util.ArrayList;

public class LikePhotoFragment extends Fragment {

    public static final Customer customer = new Customer("traveler",
            "abcdefg123",
            "kim");
    private ArrayList<String> favorite_image;

    public LikePhotoFragment() {}

    public static LikePhotoFragment newInstance(String param1, String param2) {
        LikePhotoFragment fragment = new LikePhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like_photo, container, false);

        return view;
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