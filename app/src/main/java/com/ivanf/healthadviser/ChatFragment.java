package com.ivanf.healthadviser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ChatFragment  extends Fragment {
    TextView contentView;
    String contentText = null;
    WebView webView;
    static ChatFragment me;
    static public String input = "fasfas";
    static boolean first = true;
    public static String testId = "";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        me = this;
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        contentView = (TextView) view.findViewById(R.id.contentChat);
        webView = (WebView) view.findViewById(R.id.webViewChat);

        return view;
    }

    public void GO(String zapros) {

        // если данные ранее были загружены
//        if(contentText!=null){
//            contentView.setText(contentText);
//            webView.loadData(contentText, "text/html; charset=utf-8", "utf-8");
//        }
        input = String.valueOf(Chat.POWER);

        contentView.setText("Загрузка...");
        new ProgressTask().execute(zapros);

    }

    private class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {

            String content;
            try{
                content = getContent(path[0]);
            }
            catch (IOException ex){
                content = ex.getMessage();
            }

            return content;
        }
        @SuppressLint("ResourceAsColor")
        @Override
        protected void onPostExecute(String content) {

            contentText=content;
            contentView.setText(content);
            webView.loadData(content, "text/html; charset=utf-8", "utf-8");

            input = content;
            if (first)
            {
                input = input.substring(28, input.length() - 8);
                Chat.quest = input;
                first = false;
            }
            else {
                if (input.contains("next_stage")){
                    // Idem poluchat resultat
                    if (input.contains("goto_results")) {
                        String getResult = "http://68.183.104.168:8888/health_test_results/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + Chat.key + "%22,%20%22test_id%22:%20%22" + Chat.quest + "%7D";
                        GO(getResult);
                    }
                    // Idem za drugim voprosom
                    else{
                        String sub = input.substring(26, input.length() - 3); // mb 2
                        if (sub != "nan") {
                            //Chat.number = Integer.parseInt(sub);
                            Chat.number++;
                            String getQuestion = "http://68.183.104.168:8888/health_test_get_question/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + Chat.key + "%22,%20%22test_id%22:%20%22" + Chat.quest + "%22,%20%22question_number%22:%20" + Chat.number + "%7D";
                            GO(getQuestion);
                        }
                    }
                }

                else if (input.contains("question") && !input.contains("nan")) { // Otobrazenie voprossa
                    input = Chat.getQuestion(input);
                    Chat.textQ.setText(input);
                } else if (input.contains("question") && input.contains("nan")){
                    String setResponse = "http://68.183.104.168:8888/health_test_post_answer/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + Chat.key +
                            "%22,%20%22test_id%22:%20%22" + Chat.quest + "%22,%20%22question_number%22:%20" + Chat.number + ",%20%22response%22:%20" + 0 + "%7D";
                    GO(setResponse);
                }

                // result
                else if (input.contains("diagnosis") && !input.contains("NaN") && !input.contains("nan")) { // Poluchenie res
                    Chat.result = "Sorry, but you have " + input.substring(30, input.length() - 8);
                    Chat.textQ.setText( Chat.result);

                    Chat.line.setVisibility(View.INVISIBLE);

                    Chat.sub.setText("Exit");
                    Chat.sub.setBackgroundColor(Color.rgb(216,27,96));
                    Chat.sub.setHeight(350);

                    Chat.nameAdds.setVisibility(View.VISIBLE);
                    Chat.companyAdds.setVisibility(View.VISIBLE);
                    Chat.addsImg.setVisibility(View.VISIBLE);

                    Chat.nameAdds.setText("NO-SPA");
                    Picasso.get()
                            .load("https://i.ebayimg.com/images/g/nxUAAOSwpLdZ0PJX/s-l300.jpg")
                            .into(Chat.addsImg);

                } else if (input.contains("diagnosis") && (input.contains("NaN") || input.contains("nan"))){
                    Chat.number++;
                    String getQuestion = "http://68.183.104.168:8888/health_test_get_question/%7B%22username%22:%20%22ivan.fil@gmail.com%22,%20%22session_id%22:%20%22" + Chat.key + "%22,%20%22test_id%22:%20%22" + Chat.quest + "%22,%20%22question_number%22:%20" + Chat.number + "%7D";
                    GO(getQuestion);
                }

                //if (!input.contains("question"))
                //    Chat.textQ.setText( input);

            }

        }

        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            try {
                URL url=new URL(path);
                HttpURLConnection c=(HttpURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line=reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }
}
