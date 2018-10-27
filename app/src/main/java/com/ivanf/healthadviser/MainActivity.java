package com.ivanf.healthadviser;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Recipe> recipte = new ArrayList();
    ListView recipteList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // начальная инициализация списка
        try {
            setInitialData(this);
        } catch (JSONException ex)
        {
            //bidlo  code
        } catch (IOException ex)
        {
            // bidlo code
        }
        // получаем элемент ListView
        recipteList = (ListView) findViewById(R.id.recipeList);
        // создаем адаптер
        RecipeAdapter stateAdapter = new RecipeAdapter(this, R.layout.recipe_item, states);
        // устанавливаем адаптер
        recipteList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                Recipe selectedState = (Recipe) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();
                Intent activityRecipeInfo;
                activityRecipeInfo = new Intent(MainActivity.this, RecipeInfo.class);
                RecipeInfo.Name = selectedState.getName();
                RecipeInfo.Img = selectedState.getImg();
                RecipeInfo.Company = selectedState.getCompany();
                RecipeInfo.Doc = selectedState.getDoc();
                RecipeInfo.Data_Begin = selectedState.getData_Begin();
                RecipeInfo.Data_End = selectedState.getData_Begin();
                RecipeInfo.Use = selectedState.getUse();
                RecipeInfo.Tutorial = selectedState.getTutorial();

                startActivity(activityRecipeInfo);
                finish();
            }
        };
        recipteList.setOnItemClickListener(itemListener);
    }

    private List<Recipe> states = new ArrayList();

    private void setInitialData(Context context) throws JSONException, IOException {
        String jsonText = readText(context, R.raw.your_drags);
        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray arrOfDrags = jsonRoot.getJSONArray("Your_Drugs");

        if(arrOfDrags.length()!=0){
            findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < arrOfDrags.length(); i++) {
            states.add(new Recipe (((JSONObject)arrOfDrags.get(i)).getString("Name"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Company"),
                    ((JSONObject)arrOfDrags.get(i)).getString("img"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Doc"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Data_Begin"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Data_End"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Use"),
                    ((JSONObject)arrOfDrags.get(i)).getString("Tutorial")));
        }
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Drugs) {
            Intent activityMainActivity;
            activityMainActivity = new Intent(MainActivity.this, Drugs.class);
            startActivity(activityMainActivity);
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
