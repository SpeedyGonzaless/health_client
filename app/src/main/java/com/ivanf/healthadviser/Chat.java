package com.ivanf.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Chat extends AppCompatActivity {

    //CONSTANTS
    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String ping = "http://68.183.104.168:8888/start_health_test/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22%7D";

        TextView q = (TextView) findViewById(R.id.questionText);
        ChatFragment.me.question = q;
        ChatFragment.me.GO(ping);
    }

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(Chat.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }


}
