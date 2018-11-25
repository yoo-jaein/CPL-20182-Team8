package com.example.onthejourney.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;


public class Buddy implements ClusterItem, Parcelable {
    private LatLng mPosition;
    private String name = null;
    private String mSnippet = null;
    private String buddy_id = null;
    private ArrayList<String> price_list = null;
    private int likeFlag = 0;

    public Buddy(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }
    public Buddy(double lat, double lng, String buddy_id) {
        mPosition = new LatLng(lat, lng);
        this.buddy_id = buddy_id;
    }
    public Buddy(double lat, double lng, String title, String snippet, String buddy_id, ArrayList<String> price_list) {
        mPosition = new LatLng(lat, lng);
        name = title;
        mSnippet = snippet;
        this.buddy_id = buddy_id;
        this.price_list = price_list;
    }

    protected Buddy(Parcel in) {
        mPosition = in.readParcelable(LatLng.class.getClassLoader());
        name = in.readString();
        mSnippet = in.readString();
        buddy_id = in.readString();
        in.readList(price_list, null);
        likeFlag = in.readInt();
    }

    public static final Creator<Buddy> CREATOR = new Creator<Buddy>() {
        @Override
        public Buddy createFromParcel(Parcel in) {
            return new Buddy(in);
        }

        @Override
        public Buddy[] newArray(int size) {
            return new Buddy[size];
        }
    };

    public void setmTitle(String mTitle) {
        this.name = mTitle;
    }

    public void setBuddy_id(String buddy_id){
        this.buddy_id = buddy_id;
    }
    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public String getBuddy_id() {
        return buddy_id;
    }

    public int getLikeFlag(){return this.likeFlag;}
    public void setLikeFlag(int flag){
        this.likeFlag = flag;
    }
    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mPosition, flags);
        dest.writeString(this.name);
        dest.writeString(this.mSnippet);
        dest.writeString(this.buddy_id);
        dest.writeStringList(this.price_list);
        dest.writeInt(this.likeFlag);
    }

    @Override
    public String toString() {
        return "Buddy{" +
                "mPosition=" + mPosition +
                ", name='" + name + '\'' +
                ", mSnippet='" + mSnippet + '\'' +
                ", buddy_id='" + buddy_id + '\'' +
                ", price_list=" + price_list +
                ", likeFlag=" + likeFlag +
                '}';
    }
}