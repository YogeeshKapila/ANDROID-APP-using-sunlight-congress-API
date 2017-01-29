package com.example.yogi.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Exchanger;

public class billadapter extends BaseAdapter{
    public Context context;
    public JSONArray jsonArray;

    public billadapter(Context cont, JSONArray jsa)
    {
        context = cont;
        jsonArray = jsa;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       final View customview = View.inflate(context, R.layout.billinside, null);

        ImageButton b=(ImageButton)customview.findViewById(R.id.fwdb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,billdetails.class);
                try {
                    intent.putExtra("bdet", jsonArray.getJSONObject(position).toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                context.startActivity(intent);
            }
        });


//        JSONArray sortedJsonArray = new JSONArray();
//
//        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                jsonValues.add(jsonArray.getJSONObject(i));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        Collections.sort( jsonValues, new Comparator<JSONObject>() {
//            //You can change "Name" with "ID" if you want to sort by ID
//            private static final String KEY_NAME = "introduced_on";
//
//            @Override
//            public int compare(JSONObject a, JSONObject b) {
//                String valA = new String();
//                String valB = new String();
//
//                try {
//                    valA = (String) a.get(KEY_NAME);
//                    valB = (String) b.get(KEY_NAME);
//                }
//                catch (JSONException e) {
//                    //do something
//                }
//
//                return -valA.compareTo(valB);
//                //if you want to change the sort order, simply use the following:
//                //return -valA.compareTo(valB);
//            }
//        });
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            sortedJsonArray.put(jsonValues.get(i));
//        }
//
//        jsonArray = sortedJsonArray;




        TextView t1 = (TextView) customview.findViewById(R.id.bhead);
        try {
            t1.setText(jsonArray.getJSONObject(position).getString("bill_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView t2 = (TextView) customview.findViewById(R.id.bdetails);

        try {
            t2.setText(jsonArray.getJSONObject(position).getString("official_title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        Calendar c= Calendar.getInstance();
        String intro;
        try {
            String introdt = (jsonArray.getJSONObject(position).getString("introduced_on"));

            c.setTime(f.parse(introdt));


            TextView t3 = (TextView) customview.findViewById(R.id.bintro);

            t3.setText(format.format(c.getTime()));


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return customview;
    }
}
