package com.ivanf.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends AppCompatActivity {

    //CONSTANTS
    public static String key, quest, result;
    public static int number;
    public static TextView textQ;
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Chat.number = 3;
        result = "";
        quest = "";
        first = true;

        String ping = "http://68.183.104.168:8888/start_health_test/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22%7D";

        textQ = (TextView) findViewById(R.id.questionText);
        ChatFragment.me.GO(ping);
    }

    public void onMyButtonClick(View view)
    {
        if (result == "") {
            if (!first) {
                ///health_test_post_answer/{"username": "<username>", "session_id": "<session_id>", "test_id": "<test_id>", "quesion_number": <number>, "response": <response>}
                String setResponse = "http://68.183.104.168:8888/health_test_post_answer/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key +
                        "%22,%20%22test_id%22:%20%22" + quest + "%22,%20%22question_number%22:%20" + Chat.number + ",%20%22response%22:%20" + getPower() + "%7D";

                ChatFragment.me.GO(setResponse);

            } else {
                String getQuestion = "http://68.183.104.168:8888/health_test_get_question/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22,%20%22test_id%22:%20%22" + quest + "%22,%20%22question_number%22:%20" + Chat.number + "%7D";
                ChatFragment.me.GO(getQuestion);
                first = false;
            }
        }
    }
static int POWER = -1;
    int getPower(){

        SeekBar power = (SeekBar) findViewById(R.id.seekBar);
        POWER = power.getProgress();
        return power.getProgress();
    }

    public static String getQuestion(String huina){
        return "How much do you feel " + huina.substring(29, huina.length() - 8).toLowerCase() + "?";
    }

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(Chat.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }





}
