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

        TextView q = (TextView) findViewById(R.id.questionText);
        q.setText(key);
    }

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(Chat.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }


}
