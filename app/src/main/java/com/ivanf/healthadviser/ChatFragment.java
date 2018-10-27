package com.ivanf.healthadviser;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

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
        input = "TRESH";

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
                Chat.textQ.setText(input);
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
