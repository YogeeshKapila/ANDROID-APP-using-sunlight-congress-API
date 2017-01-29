package com.example.yogi.myapplication;

import android.app.ListActivity;
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


public class leglistadapter extends BaseAdapter {
   public Context context;
   public JSONArray jsonArray;
    public String kya;

    //JSONArray legarray;

   public leglistadapter(Context cont,JSONArray jsa,String kyah) {
       context=cont;
       jsonArray=jsa;
       kya = kyah;
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
        final View customView = View.inflate(context, R.layout.legislatorlistviewinside, null);


        if(kya.equals("se") || kya.equals("ho") ) {

            JSONArray sortedJsonArray = new JSONArray();

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    jsonValues.add(jsonArray.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Collections.sort(jsonValues, new Comparator<JSONObject>() {
                //You can change "Name" with "ID" if you want to sort by ID
                private static final String KEY_NAME = "last_name";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    } catch (JSONException e) {
                        //do something
                    }

                    return valA.compareTo(valB);
                    //if you want to change the sort order, simply use the following:
                    //return -valA.compareTo(valB);
                }
            });

            for (int i = 0; i < jsonArray.length(); i++) {
                sortedJsonArray.put(jsonValues.get(i));
            }

            jsonArray = sortedJsonArray;
        }


        ImageButton bl=(ImageButton) customView.findViewById(R.id.fwdl);
        bl.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      Intent i = new Intent(context,legdetails.class);
                                      //Intent intent1 = new Intent(context, legdetails.class);
                                      try {
                                          i.putExtra("ldet", jsonArray.getJSONObject(position).toString());

                                      } catch (JSONException e) {
                                          e.printStackTrace();
                                      }
                                      context.startActivity(i);

                                  }
                              });

        TextView t1 = (TextView) customView.findViewById(R.id.name);
        try {
            t1.setText(jsonArray.getJSONObject(position).getString("last_name") +" , "+ jsonArray.getJSONObject(position).getString("first_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView t2 = (TextView) customView.findViewById(R.id.details);
        try {
            String party = jsonArray.getJSONObject(position).getString("party");
            String state = jsonArray.getJSONObject(position).getString("state_name");
            String district = jsonArray.getJSONObject(position).getString("district");
            if(district == "null")
                district="NA";
            //System.out.println(party+state+district);
            t2.setText("(" + party + ") " + state + " - " + "District - " + district);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String temp = null;
        try {
            temp = jsonArray.getJSONObject(position).getString("bioguide_id");
            //System.out.println(temp);
            String imguri = "";
            ImageView i1 = (ImageView) customView.findViewById(R.id.imgl);
            Picasso.with(context)
                    .load("https://theunitedstates.io/images/congress/original/" + temp + ".jpg")
                    .into(i1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return customView;

    }
}




