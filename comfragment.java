package com.example.yogi.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

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

public class comfragment extends Fragment {


    public comfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getContext();
        View comview = inflater.inflate(R.layout.comlayout, container, false);

        comasynctask cs=new comasynctask();
        ListView comlistView = (ListView) comview.findViewById(R.id.comlist1);
        ListView comlistView1 = (ListView) comview.findViewById(R.id.comlist2);
        ListView comlistView2 = (ListView) comview.findViewById(R.id.comlist3);
        TabHost tabHost = (TabHost)comview.findViewById(R.id.comtab);

        cs.maincontext=context;
        cs.clist=  comlistView;
        cs.clist1= comlistView1;
        cs.clist2= comlistView2;
        cs.execute("http://cs-server.usc.edu:24346/index.php?param=com");

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("comhouse");
        tabSpec.setContent(R.id.chouse);
        tabSpec.setIndicator("HOUSE");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.leglist1);

        tabSpec = tabHost.newTabSpec("comsenate");
        tabSpec.setContent(R.id.csenate);
        tabSpec.setIndicator("SENATE");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.houselist);

        tabSpec = tabHost.newTabSpec("comjoint");
        tabSpec.setContent(R.id.joint);
        tabSpec.setIndicator("JOINT");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.senatelist);


        return comview;
    }

}

class comasynctask extends AsyncTask<String, Void , String> {

    JSONArray jsonArray,jsonArray1 = new JSONArray(),jsonArray2 = new JSONArray(),jsonArray3 = new JSONArray();
    Context maincontext;
    ListView clist,clist1,clist2;


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

                else if (check.equals("senate")) {
                    jsonArray2.put(temp);
                }
                else
                {
                    jsonArray3.put(temp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        comadapter com = new comadapter(maincontext, jsonArray1);
        comadapter com1 = new comadapter(maincontext, jsonArray2);
        comadapter com2 = new comadapter(maincontext, jsonArray3);

        clist.setAdapter(com);
        clist1.setAdapter(com1);
        clist2.setAdapter(com2);

    }
}
