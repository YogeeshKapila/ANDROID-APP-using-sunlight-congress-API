package com.example.yogi.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
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


public class billfragment extends Fragment {


    public billfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Context context = getContext();
        View billview = inflater.inflate(R.layout.billlayout, container, false);

        billasynctask bs=new billasynctask();
        ListView billistView = (ListView) billview.findViewById(R.id.billact);
        ListView billistView1 = (ListView) billview.findViewById(R.id.billnew);

//        billistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("sdfkljds");
//            }
//        });

        TabHost tabHost = (TabHost)billview.findViewById(R.id.billtab);
        bs.maincontext=context;
        bs.blist=billistView;
        bs.blist1=billistView1;

        bs.execute("http://cs-server.usc.edu:24346/index.php?param=bi");

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("active");
        tabSpec.setContent(R.id.active);
        tabSpec.setIndicator("ACTIVE BILLS");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("new");
        tabSpec.setContent(R.id.newb);
        tabSpec.setIndicator("NEW BILLS");
        tabHost.addTab(tabSpec);

        return billview;
    }

}

class billasynctask extends AsyncTask<String, Void , String> {

    JSONArray jsonArray,jsonArray1 = new JSONArray(),jsonArray2 = new JSONArray();
    Context maincontext;
    ListView blist,blist1;


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
                System.out.println(jsonArray);
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
                JSONObject check = temp.getJSONObject("history");
                String check1 = check.getString("active");
                if (check1.equals("true")) {
                    jsonArray1.put(temp);
                }

                if (check1.equals("false")) {
                    jsonArray2.put(temp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

       // System.out.println(jsonArray1);
        //System.out.println(jsonArray2);

        billadapter bil = new billadapter(maincontext, jsonArray1);
        billadapter bil1 = new billadapter(maincontext, jsonArray2);

        blist.setAdapter(bil);
        blist1.setAdapter(bil1);


    }
}

