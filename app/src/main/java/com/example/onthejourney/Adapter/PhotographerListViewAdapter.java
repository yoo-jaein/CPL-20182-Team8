package com.example.onthejourney.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onthejourney.Data.MyItem;
import com.example.onthejourney.Module.RequestHttpURLConnection;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class PhotographerListViewAdapter extends BaseAdapter {

    Context context;
    private String url = "http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/";
    private ArrayList<MyItem> listViewItemList;

    public PhotographerListViewAdapter(ArrayList<MyItem> arrayList) {
        listViewItemList = arrayList;
        context = null;
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         TextView titleTextView;
         final ImageView imageView1;
         final ImageView imageView2;
         final ImageView imageView3;

        if (convertView == null) {
            context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photographer, parent, false);
        }

        titleTextView = (TextView) convertView.findViewById(R.id.textView);
        imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
        imageView2 = (ImageView)convertView.findViewById(R.id.imageView2);
        imageView3 = (ImageView)convertView.findViewById(R.id.imageView3);


        MyItem myItem = listViewItemList.get(position);

        NetworkTask networkTask = new NetworkTask("feed_items","image_path", new NetworkTask.Listener(){

            @Override
            public void onFinished(ArrayList<String> s) {
                Glide.with(context).load(url+s.get(0)).into(imageView1);
                Glide.with(context).load(url+s.get(1)).into(imageView2);
                Glide.with(context).load(url+s.get(2)).into(imageView3);
            }
        });
        networkTask.execute();

        titleTextView.setText(myItem.getTitle());



       return convertView;
    }

    public static class NetworkTask extends AsyncTask<Void, Void, ArrayList<String>> {

        public interface Listener{
            void onFinished(ArrayList<String> s);
        }

        private String option1, option2;
        private Listener listener;

        public NetworkTask(String option1, String option2, Listener listener) {
            this.option1 = option1;
            this.option2 = option2;
            this.listener = listener;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.getJsonText(option1, option2);

            return result;
        }

        protected void onPostExecute(ArrayList<String> s) {
            listener.onFinished(s);

        }
    }
}