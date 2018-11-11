package com.example.onthejourney.Module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//스트링 배열으로 바꿔서 URL들 리턴하면 더빨리 이미지 띄울수있음
public class RequestHttpURLConnection {
    String url = "http://ec2-18-222-114-158.us-east-2.compute.amazonaws.com:3000/";
    HttpURLConnection con = null;

    public ArrayList<String> getJsonText(String option1, String option2) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (option1 != null) {
            url += option1;
        }
        try {
            String jsonPage = getStringFromUrl(url);
            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("datas");
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);

                String image_path = json.getString(option2);
                arrayList.add(image_path);
            }

            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStringFromUrl(String pUrl) {

        BufferedReader bufreader = null;
        HttpURLConnection urlConnection = null;

        StringBuffer page = new StringBuffer(); //읽어온 데이터를 저장할 StringBuffer객체 생성

        try {

            //[Type1]
            /*
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(pUrl));
            InputStream contentStream = response.getEntity().getContent();
            */

            //[Type2]
            URL url = new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
            String line = null;

            //버퍼의 웹문서 소스를 줄단위로 읽어(line), Page에 저장함
            while ((line = bufreader.readLine()) != null) {
                Log.d("line:", line);
                page.append(line);
            }

            return page.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //자원해제
            try {
                bufreader.close();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }// getStringFromUrl()-------------------------


}