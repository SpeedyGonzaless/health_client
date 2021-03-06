package com.ivanf.healthadviser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.JsonReader;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Drugs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Drag> states = new ArrayList();
    ListView dragsList;

    private void setInitialData(Context context) throws JSONException, IOException {
        String jsonText = readText(context, R.raw.drugs);

        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray arrOfDrags = jsonRoot.getJSONArray("Drugs");
        for (int i = 0; i < arrOfDrags.length(); i++) {
            states.add(new Drag (((JSONObject)arrOfDrags.get(i)).getString("Name"), ((JSONObject)arrOfDrags.get(i)).getString("Company"), ((JSONObject)arrOfDrags.get(i)).getString("img"), ((JSONObject)arrOfDrags.get(i)).getString("Description")));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        dragsList = (ListView) findViewById(R.id.dragsList);
        // создаем адаптер
        DragAdapter stateAdapter = new DragAdapter(this, R.layout.drag_item, states);
        // устанавливаем адаптер
        dragsList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        final AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                Drag selectedState = (Drag)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedState.getName() + "was chosen",
                        Toast.LENGTH_SHORT).show();
                Intent activityDrugInfo;
                activityDrugInfo = new Intent(Drugs.this, DrugInfo.class);
                DrugInfo.Name = selectedState.getName();
                DrugInfo.img = selectedState.getFlagResource();
                DrugInfo.Company = selectedState.getCapital();
                DrugInfo.Descr = selectedState.getDescr();

                startActivity(activityDrugInfo);
                finish();
            }
        };
        dragsList.setOnItemClickListener(itemListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cahtAct;
                cahtAct = new Intent(Drugs.this, Chat.class);

                startActivity(cahtAct);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent activityMainActivity;
        activityMainActivity = new Intent(Drugs.this, MainActivity.class);
        startActivity(activityMainActivity);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drugs, menu);
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

        if (id == R.id.Main) {
            Intent activityMainActivity;
            activityMainActivity = new Intent(Drugs.this, MainActivity.class);
            startActivity(activityMainActivity);
            finish();

        } else if (id == R.id.nav_chat) {
            Intent activityMainActivity;
            activityMainActivity = new Intent(Drugs.this, Chat.class);
            startActivity(activityMainActivity);
            finish();

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
