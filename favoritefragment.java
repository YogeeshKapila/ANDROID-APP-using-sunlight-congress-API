package com.example.yogi.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.yogi.myapplication.MainActivity.favvbil;
import static com.example.yogi.myapplication.MainActivity.favvcom;
import static com.example.yogi.myapplication.MainActivity.favvleg;


public class favoritefragment extends Fragment {


    public favoritefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getContext();
        View favview = inflater.inflate(R.layout.fragment_favoritefragment, container, false);


        ListView favlistView = (ListView) favview.findViewById(R.id.favleglist);
        ListView favlistView1 = (ListView) favview.findViewById(R.id.favcomlist);
        ListView favlistView2 = (ListView) favview.findViewById(R.id.favbilllist);
        TabHost tabHost = (TabHost)favview.findViewById(R.id.favtab);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("favvleg");
        tabSpec.setContent(R.id.favleg);
        tabSpec.setIndicator("LEGISLATORS");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.leglist1);

        tabSpec = tabHost.newTabSpec("favvcom");
        tabSpec.setContent(R.id.favcom);
        tabSpec.setIndicator("COMMITTEES");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.houselist);
      //  legdetails.favorites=2;

        tabSpec = tabHost.newTabSpec("favvbil");
        tabSpec.setContent(R.id.favbill);
        tabSpec.setIndicator("BILLS");
        tabHost.addTab(tabSpec);
        //tabSpec.setContent(R.id.senatelist);

        JSONArray favjsonArray = new JSONArray(),favjsonArray2 = new JSONArray(),favjsonArray3 = new JSONArray();

        System.out.println("favbil: "+favvbil);
        System.out.println("favleg: "+favvleg);
        System.out.println("favcom: "+favvcom);

        favjsonArray = favvleg;
        favjsonArray3 = favvbil;
        favjsonArray2 = favvcom;

        JSONArray sortedJsonArray1 = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < favjsonArray.length(); i++) {
            try {
                jsonValues.add(favjsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "last_name";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < favjsonArray.length(); i++) {
            sortedJsonArray1.put(jsonValues.get(i));
        }

        favjsonArray = sortedJsonArray1;

        JSONArray sortedJsonArray3 = new JSONArray();

        jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < favjsonArray3.length(); i++) {
            try {
                jsonValues.add(favjsonArray3.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "introduced_on";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                }
                catch (JSONException e) {
                    //do something
                }

                return -valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < favjsonArray3.length(); i++) {
            sortedJsonArray3.put(jsonValues.get(i));
        }

        favjsonArray3 = sortedJsonArray3;


        JSONArray sortedJsonArray2 = new JSONArray();

        jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < favjsonArray2.length(); i++) {
            try {
                jsonValues.add(favjsonArray2.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "committee_name";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < favjsonArray2.length(); i++) {
            sortedJsonArray2.put(jsonValues.get(i));
        }

        favjsonArray2 = sortedJsonArray2;


        leglistadapter fale = new leglistadapter(context, favjsonArray,"aa");
        comadapter faco = new comadapter(context, favjsonArray2);
        billadapter fabi = new billadapter(context, favjsonArray3);

        favlistView.setAdapter(fale);
        favlistView1.setAdapter(faco);
        favlistView2.setAdapter(fabi);

        return favview;
    }

}
