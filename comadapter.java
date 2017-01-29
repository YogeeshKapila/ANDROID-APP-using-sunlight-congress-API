package com.example.yogi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.yogi.myapplication.MainActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class comadapter extends BaseAdapter {
    public Context context;
    public JSONArray jsonArray;

    //JSONArray legarray;

    public comadapter(Context cont,JSONArray jsa) {
        context=cont;
        jsonArray=jsa;

    }


    @Override
    public int getCount() {
        // System.out.println(jsonArray.length());
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
        // LayoutInflater leginflater = LayoutInflater.from(context);

        final  View customView = View.inflate(context, R.layout.cominside, null);

        ImageButton bc = (ImageButton) customView.findViewById(R.id.fwdc);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ic = new Intent(context,comdetails.class);
                try {
                    ic.putExtra("cdet",jsonArray.getJSONObject(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                context.startActivity(ic)   ;
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
//            private static final String KEY_NAME = "committee_name";
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
//                return valA.compareTo(valB);
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





        TextView t1 = (TextView) customView.findViewById(R.id.comid);
        try {
            t1.setText(jsonArray.getJSONObject(position).getString("committee_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView t2 = (TextView) customView.findViewById(R.id.comdetails);
        try {
            String cname = jsonArray.getJSONObject(position).getString("name");
            t2.setText(cname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView t3 = (TextView) customView.findViewById(R.id.comchamber);
        try {
            String cchamber = jsonArray.getJSONObject(position).getString("chamber");
            t3.setText(cchamber);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return customView;
    }
}
