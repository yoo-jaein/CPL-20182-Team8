package com.example.onthejourney.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onthejourney.Data.CheckList;
import com.example.onthejourney.R;

import java.util.ArrayList;

public class RequestAdapter extends BaseAdapter {

    private ArrayList<CheckList> checkLists = new ArrayList<>();

    public RequestAdapter(ArrayList<CheckList> list){
        this.checkLists = list;
    }
    @Override
    public int getCount() {
        return checkLists.size();
    }

    @Override
    public Object getItem(int position) {
        return checkLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, null, false);

            holder = new CustomViewHolder();
            holder.buddyId = convertView.findViewById(R.id.item_request_buddyId);
            holder.startDate = convertView.findViewById(R.id.item_request_start);
            holder.endDate = convertView.findViewById(R.id.item_request_end);
            holder.location = convertView.findViewById(R.id.item_request_location);
            holder.people_number = convertView.findViewById(R.id.item_request_people_number);
            holder.suggetPrice = convertView.findViewById(R.id.item_request_suggestPrice);
            holder.suggest1 = convertView.findViewById(R.id.item_request_suggest1);

            convertView.setTag(holder);
        }else{
            holder = (CustomViewHolder)convertView.getTag();
        }
        CheckList checkList = checkLists.get(position);
        holder.buddyId.setText("Buddy_id : " + checkList.getBuddy_id());
        holder.startDate.setText(checkList.getStart_time().toString());
        holder.endDate.setText(checkList.getEnd_time().toString());
        holder.location.setText(checkList.getLocation());
        holder.people_number.setText(""+checkList.getPeople_number());
        holder.suggetPrice.setText(""+ checkList.getSuggested_price() +"원");
        Log.d("Checklist requirement",checkList.getRequirement_list().toString());
        if(checkList.getRequirement_list().get(0) != null){
            Log.d("0",checkList.getRequirement_list().get(0));
            holder.suggest1.setText(checkList.getRequirement_list().get(0));
        }

        return convertView;

    }

    class CustomViewHolder {
        TextView buddyId;
        TextView startDate, endDate;
        TextView location;
        TextView people_number;
        TextView suggest1, suggest2, suggest3;
        TextView suggetPrice;
    }
}
