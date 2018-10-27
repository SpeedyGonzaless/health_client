package com.ivanf.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends AppCompatActivity {

    //CONSTANTS
    public static String key, quest;
    public static TextView textQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String ping = "http://68.183.104.168:8888/start_health_test/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22%7D";

        textQ = (TextView) findViewById(R.id.questionText);
        ChatFragment.me.GO(ping);
    }

    public void onMyButtonClick(View view)
    {
        //quest = "EaF0e3nPYaMIR21cS8uijv2AIbV";
        String getQuestion = "http://68.183.104.168:8888/health_test_get_question/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22,%20%22test_id%22:%20%22"+ quest +"%22,%20%22question_number%22:%203%7D";
        ChatFragment.me.GO(getQuestion);
    }

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(Chat.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }





}
