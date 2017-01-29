package com.example.yogi.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
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

import static android.R.attr.button;


public class legfragment extends Fragment {


    public legfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final Context context = getContext();
        View legview = inflater.inflate(R.layout.leglayout, container, false);

        legasynctask ls=new legasynctask();
        ListView leglistView = (ListView) legview.findViewById(R.id.leglist1);
        ListView leglistView1 = (ListView) legview.findViewById(R.id.houselist);
        ListView leglistView2 = (ListView) legview.findViewById(R.id.senatelist);
        TabHost tabHost = (TabHost)legview.findViewById(R.id.legtab);

        leglistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        //leglistView.setOnItemClickListener(R.id);
        ls.maincontext=context;
        ls.list=leglistView;
        ls.list1=leglistView1;
        ls.list2=leglistView2;
        ls.execute("http://cs-server.usc.edu:24346/index.php?param=legis");

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("state");
        tabSpec.setContent(R.id.state);
        tabSpec.setIndicator("BY STATES");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.leglist1);

        tabSpec = tabHost.newTabSpec("house");
        tabSpec.setContent(R.id.house);
        tabSpec.setIndicator("HOUSE");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.houselist);

        tabSpec = tabHost.newTabSpec("senate");
        tabSpec.setContent(R.id.senate);
        tabSpec.setIndicator("SENATE");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.senatelist);


        return legview;
    }

}

class legasynctask extends AsyncTask<String, Void , String> {

    JSONArray jsonArray,jsonArray1 = new JSONArray(),jsonArray2 = new JSONArray();
    Context maincontext;
    ListView list,list1,list2;


    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);

            }
            JSONObject legjson = null;
            try {
                legjson = new JSONObject(data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonArray = legjson.getJSONArray("results");
                //System.out.println(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //System.out.println(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            urlConnection.disconnect();
        }
        //   System.out.println(jsonArray);
        //System.out.print("2");
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        JSONObject temp = new JSONObject();

        for (int i = 0, j = 0, k = 0; i < jsonArray.length(); i++) {

            try {
                temp = jsonArray.getJSONObject(i);
                // System.out.println(temp);
                String check = temp.getString("chamber");
                if (check.equals("house")) {
                    jsonArray1.put(temp);
                }

                if (check.equals("senate")) {
                    jsonArray2.put(temp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        leglistadapter leg = new leglistadapter(maincontext, jsonArray,"st");
        leglistadapter leg1 = new leglistadapter(maincontext, jsonArray1,"se");
        leglistadapter leg2 = new leglistadapter(maincontext, jsonArray2,"ho");

        list.setAdapter(leg);
        list1.setAdapter(leg1);
        list2.setAdapter(leg2);
    }
}

