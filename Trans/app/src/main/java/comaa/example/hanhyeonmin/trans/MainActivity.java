package comaa.example.hanhyeonmin.trans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    private TextView myText;
    private String action = "GET";
    private String path = "buddies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = findViewById(R.id.text);


        /*
        new HttpAsyncTask("GET", "buddies", new TypeToken<ResultBody<Buddy>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Buddy> result = (ResultBody<Buddy>) resultBody;
                        myText.setText(result.getDatas().get(0).getName());
                    }
                }).execute();

**/


        new HttpAsyncTask("GET", "feed_items", new TypeToken<ResultBody<FeedItem>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<FeedItem> result = (ResultBody<FeedItem>) resultBody;
                        myText.setText(result.getDatas().get(0).getImage_path());
                    }
                }).execute();

    }
}
