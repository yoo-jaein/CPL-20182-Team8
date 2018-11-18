package com.example.onthejourney.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Buddy implements ClusterItem, Parcelable {
    private LatLng mPosition;
    private String mTitle = null;
    private String mSnippet = null;
    private String buddy_id = null;

    public Buddy(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }
    public Buddy(double lat, double lng, String buddy_id) {
        mPosition = new LatLng(lat, lng);
        this.buddy_id = buddy_id;
    }
    public Buddy(double lat, double lng, String title, String snippet, String buddy_id) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        this.buddy_id = buddy_id;
    }

    protected Buddy(Parcel in) {
        mPosition = in.readParcelable(LatLng.class.getClassLoader());
        mTitle = in.readString();
        mSnippet = in.readString();
        buddy_id = in.readString();
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
        this.mTitle = mTitle;
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
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public String getBuddy_id() {
        return buddy_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Buddy{" +
                "mPosition=" + mPosition +
                ", mTitle='" + mTitle + '\'' +
                ", mSnippet='" + mSnippet + '\'' +
                ", buddy_id='" + buddy_id + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mPosition, flags);
        dest.writeString(this.mTitle);
        dest.writeString(this.mSnippet);
        dest.writeString(this.buddy_id);
    }
}