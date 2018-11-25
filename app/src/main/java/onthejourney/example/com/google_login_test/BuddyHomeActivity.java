package onthejourney.example.com.google_login_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BuddyHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_home);

        Intent intent = getIntent();
        Buddy buddy_info = (Buddy)intent.getParcelableExtra("BuddyAccount");

        Toast.makeText(getApplicationContext(), "안녕 나는 " + buddy_info.getName() + " buddy에요~", Toast.LENGTH_LONG).show();
    }
}
