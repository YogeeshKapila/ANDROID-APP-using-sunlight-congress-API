package com.example.yogi.myapplication;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextClock;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.widget.TabHost.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static JSONArray favvleg = new JSONArray();
    public final static JSONArray favvbil = new JSONArray();
    public final static JSONArray favvcom = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        legfragment myFragment = new legfragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.add(R.id.content_main, myFragment).commit();

        legfragment lfi= new legfragment();
        FragmentManager managerl = getSupportFragmentManager();
        managerl.beginTransaction().replace(R.id.content_main, lfi).commit();

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

        if (id == R.id.nav_camera) {;

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Legislators");
            legfragment lf= new legfragment();
            FragmentManager managerl = getSupportFragmentManager();
            managerl.beginTransaction().replace(R.id.content_main, lf).commit();


        } else if (id == R.id.nav_gallery) {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Bills");
            billfragment bf= new billfragment();
            FragmentManager managerb = getSupportFragmentManager();
            managerb.beginTransaction().replace(R.id.content_main, bf).commit();


        } else if (id == R.id.nav_slideshow) {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Commitees");
            comfragment cf= new comfragment();
            FragmentManager managerc = getSupportFragmentManager();
            managerc.beginTransaction().replace(R.id.content_main, cf).commit();

        } else if (id == R.id.nav_manage) {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Favourites");
            favoritefragment ff= new favoritefragment();
            FragmentManager managerf = getSupportFragmentManager();
            managerf.beginTransaction().replace(R.id.content_main, ff).commit();

        } else if (id == R.id.nav_share) {

            Intent iintent = new Intent(MainActivity.this,aboutyogi.class);
            startActivity(iintent);

//            aboutme abtme= new aboutme();
//            FragmentManager managerf = getSupportFragmentManager();
//            managerf.beginTransaction().replace(R.id.content_main, abtme).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
