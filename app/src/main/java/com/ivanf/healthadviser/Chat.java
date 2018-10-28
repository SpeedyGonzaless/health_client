package com.ivanf.healthadviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    //CONSTANTS
    public static String key, quest, result;
    public static int number;
    public static TextView textQ;
    public static Button sub;
    public static SeekBar line;
    boolean first = true;

    static public ImageView addsImg;
    static public TextView nameAdds, companyAdds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Chat.number = 3;
        result = "";
        quest = "";
        first = true;
        ChatFragment.first = true;
        addsImg = (ImageView) findViewById(R.id.imageAdds);
        nameAdds = (TextView) findViewById(R.id.addsName);
        companyAdds = (TextView) findViewById(R.id.addsComp);
        sub = (Button) findViewById(R.id.subButton);
        line = (SeekBar) findViewById(R.id.seekBar);

        String ping = "http://68.183.104.168:8887/start_health_test/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22%7D";

        textQ = (TextView) findViewById(R.id.questionText);
        ChatFragment.me.GO(ping);
    }

    public void onMyButtonClick(View view)
    {
        if (result == "") {
            if (!first) {
                ///health_test_post_answer/{"username": "<username>", "session_id": "<session_id>", "test_id": "<test_id>", "quesion_number": <number>, "response": <response>}
                String setResponse = "http://68.183.104.168:8887/health_test_post_answer/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key +
                        "%22,%20%22test_id%22:%20%22" + quest + "%22,%20%22question_number%22:%20" + Chat.number + ",%20%22response%22:%20" + getPower() + "%7D";

                ChatFragment.me.GO(setResponse);


            } else {
                String getQuestion = "http://68.183.104.168:8887/health_test_get_question/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + key + "%22,%20%22test_id%22:%20%22" + quest + "%22,%20%22question_number%22:%20" + Chat.number + "%7D";
                ChatFragment.me.GO(getQuestion);
                first = false;
            }
        }
        else if (Chat.sub.getText() == "Are you sure?"){
            Intent main;
            main = new Intent(Chat.this, MainActivity.class);
            result="";
            startActivity(main);
            finish();
        } else {
            Chat.sub.setText("Are you sure?");
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
