package comaa.example.hanhyeonmin.trans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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
        new HttpAsyncTask("GET", "buddies", null, new TypeToken<ResultBody<Buddy>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Buddy> result = (ResultBody<Buddy>) resultBody;
                        myText.setText(result.getDatas().get(0).getName());
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


        ArrayList<Pair<String, String>> requstBodyContent = new ArrayList<>();
        requstBodyContent.add(new Pair<>("customer_id", "hhh"));
        requstBodyContent.add(new Pair<>("pw", "1234"));
        requstBodyContent.add(new Pair<>("name", "hiroo~"));
        requstBodyContent.add(new Pair<>("nickname", "1234"));

        ArrayList<String> favorite_buddy_id_list = new ArrayList();
        favorite_buddy_id_list.add("hhm");
        favorite_buddy_id_list.add("hiroo~");
        favorite_buddy_id_list.add("hihi");

        String favorite_buddy_id_str = "[ ";
        for(String s : favorite_buddy_id_list){
            favorite_buddy_id_str += s + ", ";
        }
        favorite_buddy_id_str = favorite_buddy_id_str.substring(0, favorite_buddy_id_str.length()-1);
        favorite_buddy_id_str  += " ]";

        requstBodyContent.add(new Pair<>("favorite_feed_item_id_list", favorite_buddy_id_str));

        new HttpAsyncTask("POST", "customers", requstBodyContent, new TypeToken<ResultBody<Customer>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        ResultBody<Customer> result = (ResultBody<Customer>) resultBody;
                        myText.setText(result.getDatas().get(0).getName());
                    }
                })
                .execute();

    }
}
