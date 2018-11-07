package com.example.onthejourney.Data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;

public class MyItem implements ClusterItem, Parcelable {
    private LatLng mPosition;
    private String mTitle = null;
    private String mSnippet = null;
    private ArrayList<Bitmap> imageByteList= null;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public MyItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        imageByteList = new ArrayList<Bitmap>();
    }

    protected MyItem(Parcel in) {
        mPosition = in.readParcelable(LatLng.class.getClassLoader());
        mTitle = in.readString();
        mSnippet = in.readString();

        imageByteList = in.readArrayList(ArrayList.class.getClassLoader());
    }

    public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
        @Override
        public MyItem createFromParcel(Parcel in) {
            return new MyItem(in);
        }

        @Override
        public MyItem[] newArray(int size) {
            return new MyItem[size];
        }
    };
    public void addImageByteToList(Bitmap bitmap){
        imageByteList.add(bitmap);
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "mPosition=" + mPosition +
                ", mTitle='" + mTitle + '\'' +
                ", mSnippet='" + mSnippet + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mPosition, flags);
        dest.writeString(this.mTitle);
        dest.writeString(this.mSnippet);
        dest.writeList(this.imageByteList);
    }
}