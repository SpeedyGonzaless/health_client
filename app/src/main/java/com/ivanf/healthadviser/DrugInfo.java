package com.ivanf.healthadviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class DrugInfo extends AppCompatActivity {
    static public String Name, Company, img, Descr;

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(DrugInfo.this, Drugs.class);
        startActivity(activityMainActivity);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cahtAct;
                cahtAct = new Intent(DrugInfo.this, Chat.class);

                startActivity(cahtAct);
                finish();
            }
        });


        AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_rec);
//        ImageView time = (ImageView) findViewById(R.id.timeImg);
//        Picasso.get()
//                .load(img)
//                .fit()
//                .into(time);

        TextView name = (TextView) findViewById(R.id.infoText);
        name.setText(Name);

        TextView des = (TextView) findViewById(R.id.description_info);
        des.setText(Html.fromHtml(Descr));
    }
}
