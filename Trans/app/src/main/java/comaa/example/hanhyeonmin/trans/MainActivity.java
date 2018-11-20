package comaa.example.hanhyeonmin.trans;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView myText;
    private String action = "GET";
    private String path = "buddies";


    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            Toast.makeText(MainActivity.this, "permission granted", Toast.LENGTH_SHORT).show();
            sendFeed();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    void sendFeed(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("buddy_id", "hhh");
            jsonObject.put("image_path", "");
            jsonObject.put("favorite_num", "5");
            jsonObject.put("picture_taken_location", "daegu");

            JSONArray array = new JSONArray();
            array.put("wedding");
            array.put("~");
            array.put("funny");

            jsonObject.put("hashtag", array);
        }catch (Exception e){
            e.printStackTrace();
        }

        Drawable drawable = getResources().getDrawable(R.drawable.such1);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        File file;
        String path = Environment.getExternalStorageDirectory().toString();
        file = new File(path, "such1.png");
        try{
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(file.getName());

        new HttpAsyncTask("POST", "feed_items", jsonObject, file, new TypeToken<ResultBody<FeedItem>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<FeedItem> result = (ResultBody<FeedItem>) resultBody;
                        myText.setText(result.getDatas().get(0).getImage_path());
                    }
                }).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = findViewById(R.id.text);


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("customer_id", "abcd");
            jsonObject.put("name", "100");
            jsonObject.put("nickname", "hello");
        }catch (Exception e){
            e.printStackTrace();
        }

        new HttpAsyncTask("POST", "customers", jsonObject, null, new TypeToken<ResultBody<Customer>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
                        myText.setText(result.getDatas().get(0).getName());
                    }
                }).execute();


        /*
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("buddy_id", "abcd");
            jsonObject.put("favorite_num", "100");


            JSONArray array = new JSONArray();
            array.put("wedding");
            array.put("hiroo~");
            array.put("hihi");

            jsonObject.put("hashtag", array);
        }catch (Exception e){
            e.printStackTrace();
        }

        new HttpAsyncTask("POST", "feed_items", jsonObject, null, new TypeToken<ResultBody<FeedItem>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<FeedItem> result = (ResultBody<FeedItem>) resultBody;
                        myText.setText(result.getDatas().get(0).);
                    }
                }).execute();
        */

        /*
        new HttpAsyncTask("GET", "feed_items", null, new TypeToken<ResultBody<FeedItem>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<FeedItem> result = (ResultBody<FeedItem>) resultBody;
                        myText.setText(result.getDatas().get(0).getImage_path());
                    }
                }).execute();

        */


        /*
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("customer_id", "knu");
            jsonObject.put("buddy_id", "hoot~");
            jsonObject.put("state", "finished");
            jsonObject.put("start_time", "2018-11-01");
            jsonObject.put("end_time", "2018-11-02");
            jsonObject.put("location", "daegu");
            jsonObject.put("people_number", "1");
            jsonObject.put("suggested_price", "1");


            JSONArray array = new JSONArray();
            array.put("hhm");
            array.put("hiroo~");
            array.put("hihi");

            jsonObject.put("requirement_list", array);
        }catch (Exception e){
            e.printStackTrace();
        }


        new HttpAsyncTask("POST", "customers", jsonObject, null, new TypeToken<ResultBody<Customer>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
                        myText.setText(result.getDatas().get(0).getName());
                    }
                })
                .execute();


        //this.requestPermission();
        sendFeed();
        */
    }
}
