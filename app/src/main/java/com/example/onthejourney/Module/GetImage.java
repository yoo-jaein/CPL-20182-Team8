package com.example.onthejourney.Module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.onthejourney.Activity.MapsActivity;
import com.example.onthejourney.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetImage extends AsyncTask<Object, Void, Bitmap> {
    Bitmap Bbit;
    @Override
    protected Bitmap doInBackground(Object... params) {
        // 네트워크에 접속해서 데이터를 가져옴
        String urlurl = "http://hyunminh.iptime.org:3000/images/IMG_0316.JPG"; //이건 내 서버 주소, 서버 주소에 이미지 경로 붙여주면 이미지 url
        try {
            //웹사이트에 접속 (사진이 있는 주소로 접근)
            URL Url = new URL(urlurl);
            // 웹사이트에 접속 설정
            URLConnection urlcon = Url.openConnection();

            urlcon.setDoInput(true);
            urlcon.setDoOutput(true);
            urlcon.connect();

            InputStream is = urlcon.getInputStream();
            Bbit = BitmapFactory.decodeStream(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Bbit;
    }
}