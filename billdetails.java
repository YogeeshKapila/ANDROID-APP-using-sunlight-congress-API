package com.example.yogi.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.yogi.myapplication.MainActivity.favvbil;
import static com.example.yogi.myapplication.MainActivity.favvleg;
import static com.example.yogi.myapplication.R.id.home;
import static com.example.yogi.myapplication.R.styleable.MenuItem;

public class billdetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetails);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        String detb = getIntent().getExtras().getString("bdet");
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(detb);
            System.out.println(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView t1 = (TextView) findViewById(R.id.bid);
        try {
            String b;
            if(obj.has("bill_id"))
            {
                b = obj.getString("bill_id");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.btitle);
        try {
            String b;
            if(obj.has("official_title")) {
                b = obj.getString("official_title");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.btype);
        try {
            String b;
            if(obj.has("bill_type")) {
                b = obj.getString("bill_type");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.bsponsor);
        try {
            if(obj.has("sponsor")) {
                JSONObject temp = obj.getJSONObject("sponsor");
                String b1 = temp.getString("title");
                String b2 = temp.getString("last_name");
                String b3 = temp.getString("first_name");

                t1.setText(b1 + ", " + b2 + " " + b3);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.bchamber);
        try {
            String b;
            if(obj.has("chamber")) {
                b = obj.getString("chamber");
                t1.setText(b);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.bstatus);
        try {
            if (obj.has("history"))
            {
                JSONObject temp = obj.getJSONObject("history");
                 if(temp.has("active"))
                 {
                     String b = temp.getString("active");
                     if (b == "null")
                         b = "N.A.";
                     else {
                         if (b.equals("true"))
                             t1.setText("Active");
                         else
                             t1.setText("New");
                     }
                 }
                 else {t1.setText("N.A.");}
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.bintroon);
        try {
            String b;
            if(obj.has("introduced_on")) {
                b = obj.getString("introduced_on");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.bconurl);
        try {
            if(obj.has("urls")) {
                JSONObject temp = obj.getJSONObject("urls");
                String b;
                if (temp.has("congress")) {
                    b = temp.getString("congress");
                    if (b == "null")
                        b = "N.A.";
                    t1.setText(b);
                }
                else {t1.setText("N.A.");}
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.bverstat);
        try {
            if (obj.has("last_verion")) {
                JSONObject temp = obj.getJSONObject("last_version");
                String b;
                if(temp.has("version_name")) {
                    b = temp.getString("version_name");
                    if (b == "null")
                        b = "N.A.";
                    t1.setText(b);
                }
                else {t1.setText("N.A.");}
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.burl);
        try {
            if(obj.has("last_version")) {
                JSONObject temp = obj.getJSONObject("last_version");
                if(temp.has("urls")) {
                    JSONObject tempo = temp.getJSONObject("urls");
                    if(tempo.has("pdf")) {
                        String b = tempo.getString("pdf");
                        if (b == "null")
                            b = "N.A.";
                        t1.setText(b);
                    }
                    else {t1.setText("N.A.");}
                }
                else {t1.setText("N.A.");}
            }
            else {t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ImageButton b5=(ImageButton) findViewById(R.id.starb);

        int length = favvbil.length();

        boolean hknb=false;
        for(int i=0;i<length;i++) {
            try {
                if (favvbil.getJSONObject(i).getString("bill_id").equals(obj.getString("bill_id"))) {
                    hknb = true;
                    //System.out.print("haiii");
                    //b4.setTag(2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(!hknb) {
            b5.setImageResource(R.drawable.star);
            //System.out.print("nai  haiii");
            // b4.setTag(1);
        }
        else
        {
            b5.setImageResource(R.drawable.starfull);
            //System.out.print("haiii");
        }

        final JSONObject finalObj = obj;
        b5.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {

                int length = favvbil.length();

                boolean hknb=false; int pos = 0;
                for(int i=0;i<length;i++) {
                    try {
                        if (favvbil.getJSONObject(i).getString("bill_id").equals(finalObj.getString("bill_id"))) {
                            hknb = true;
                            pos=i;
                            //b4.setTag(2);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(!hknb) {
                    //System.out.println(finalObj);
                    favvbil.put(finalObj);
                    System.out.println("favvb"+favvbil);
                    b5.setImageResource(R.drawable.starfull);
                    // b4.setTag(1);
                }
                else
                {
                    favvbil.remove(pos);
                    b5.setImageResource(R.drawable.star);
                    System.out.println("favvb"+favvbil);
                }

            }


        });



            System.out.println("favvbil:"+favvbil);

    }





//    @Override
//    public boolean onOptionsItemSelected(android.view.MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
