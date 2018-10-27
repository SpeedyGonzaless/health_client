package com.ivanf.healthadviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RecipeInfo extends AppCompatActivity {
    static public String Name, Company, Img, Doc, Data_Begin, Data_End, Use, Tutorial;

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(RecipeInfo.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_rec);
//        ImageView time = (ImageView) findViewById(R.id.timeImg);
//        Picasso.get()
//                .load(img)
//                .fit()
//                .into(time);

        TextView name = (TextView) findViewById(R.id.infoRecipe);
        name.setText(Name);

        TextView Tut = (TextView) findViewById(R.id.Recipe_Descr);
        Tut.setText(Tutorial);
    }
}
