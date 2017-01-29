package com.example.yogi.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.yogi.myapplication.MainActivity.favvleg;

public class legdetails extends AppCompatActivity {

    public Context context;
   // public static JSONArray favbill;

    public legdetails() {
        context = this;
       // favbill = new JSONArray();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legdetails);

//        getActionBar().setDisplayHomeAsUpEnabled(true);


        String detl = getIntent().getExtras().getString("ldet");
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(detl);
            System.out.println(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            String b;
            b = obj.getString("bioguide_id");
            //System.out.println(temp);
            String imguri = "";
            ImageView i1 = (ImageView) findViewById(R.id.limg);
            Picasso.with(context)
                    .load("https://theunitedstates.io/images/congress/original/" + b + ".jpg")
                    .into(i1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String b;
            b = obj.getString("party");
            //System.out.println(temp);

            ImageView i1 = (ImageView) findViewById(R.id.limgp);
            TextView t1 = (TextView) findViewById(R.id.lpartydet);
            if (b.equals("R")) {
                i1.setImageResource(R.drawable.r);
                t1.setText("Republican");
            } else {
                i1.setImageResource(R.drawable.s);
                t1.setText("Democrat");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView t1 = (TextView) findViewById(R.id.legname);
        try {
            String b1 = obj.getString("title");
            String b2 = obj.getString("last_name");
            String b3 = obj.getString("first_name");
            t1.setText(b1 + ", " + b2 + " " + b3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legemail);
        try {
            String b;
            if (obj.has("oc_email")) {
                b = obj.getString("oc_email");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legchamber);
        try {
            String b = obj.getString("chamber");
            if (b == "null")
                b = "N.A.";
            t1.setText(b);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legcontact);
        try {
            String b;
            if (obj.has("phone")) {
                b = obj.getString("phone");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.start);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        Calendar c= Calendar.getInstance();
        try {
            String b;
            if (obj.has("term_start")) {
                b = obj.getString("term_start");
                if (b == "null") {
                    t1.setText("N.A.");
                }
                else {
                    c.setTime(f.parse(b));
                    t1.setText(format.format(c.getTime()));
                }
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.end);
        try {
            String b;
            if (obj.has("term_end")) {
                b = obj.getString("term_end");
                if (b == "null"){
                    t1.setText("N.A.");
                }
                else {
                    c.setTime(f.parse(b));
                    t1.setText(format.format(c.getTime()));
                }
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ProgressBar p1 = (ProgressBar) findViewById(R.id.progressBar);
        TextView p2 = (TextView) findViewById(R.id.yo);
        try {

            SimpleDateFormat my = new SimpleDateFormat("yyyy-MM-dd");
            String curr = my.format(new Date());
            String b1 = obj.getString("term_start");
            String b2 = obj.getString("term_end");
            Date d1 = my.parse(b1);
            Date d2 = my.parse(b2);
            Date d3 = my.parse(curr);
            //System.out.println(d1+"  "+d2+"  "+d3);
            long diff = (d3.getTime() - d1.getTime())/(24*60*60*1000);
            long diff1 = (d2.getTime() - d1.getTime())/(24*60*60*1000);
           //System.out.println(diff+"    "+diff1);
            float p = (float)((diff*100)/diff1);
           // perr.setText(per);
           // System.out.println("per"+p);
            int ff = (int) p;
            String ss = String.valueOf(ff);
            p2.setText(ss+"%");
            p1.setProgress(ff);
            p1.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legoff);
        try {
            String b;
            if (obj.has("office")) {
                b = obj.getString("office");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legstateabb);
        try {
            String b = obj.getString("state");
            if (b == "null")
                b = "N.A.";
            t1.setText(b);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legfax);
        try {
            String b;
            if (obj.has("fax")) {
                b = obj.getString("fax");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            } else {
                t1.setText("N.A.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        t1 = (TextView) findViewById(R.id.legbday);
        try {
            String b;
            if (obj.has("birthday"))
            {
                b = obj.getString("birthday");
                if (b == "null")
                {
                    t1.setText("N.A.");
                }
                else
                {
                    c.setTime(f.parse(b));
                    t1.setText(format.format(c.getTime()));
                }
            }
            else
            {
                    t1.setText("NA");
                }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fb = null;
        if(obj.has("facebook_id"))
        {
            try {
                fb = obj.getString("facebook_id");
                if(fb=="null")
                    fb="N.A.";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {fb="N.A.";}

        String tw = null;
        if(obj.has("twitter_id"))
        {
            try {
                tw = obj.getString("twitter_id");
                if(tw=="null")
                    tw="N.A.";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else {tw="N.A.";}

        String w = null;
        if(obj.has("website"))
        {
            try {
                w = obj.getString("website");
                if(w=="null")
                    w="N.A.";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {w="N.A.";}

        ImageButton bl=(ImageButton) findViewById(R.id.fb);
        final String finalFb = fb;
        bl.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                if (finalFb !="N.A.") {
                    WebView webfb = new WebView(getApplicationContext());
                    setContentView(webfb);
                    webfb.loadUrl("http://www.facebook.com/" + finalFb);
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO Facebook page for this person", Toast.LENGTH_LONG).show();
                }
                }


        });

        ImageButton b2=(ImageButton) findViewById(R.id.tw);
        final String finaltw = tw;
        b2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                if (finaltw !="N.A.") {
                    WebView webfb = new WebView(getApplicationContext());
                    setContentView(webfb);
                    webfb.loadUrl("http://www.facebook.com/" + finaltw);
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO twitter page for this person", Toast.LENGTH_LONG).show();
                }
            }


        });

        ImageButton b3=(ImageButton) findViewById(R.id.w);
        final String finalw = w;
        b3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                if (finalw !="N.A.") {
                    WebView webfb = new WebView(getApplicationContext());
                    setContentView(webfb);
                    webfb.loadUrl(finalw);
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO Web page for this person", Toast.LENGTH_LONG).show();
                }
            }


        });



        final ImageButton b4=(ImageButton) findViewById(R.id.star);

        int length = favvleg.length();

        boolean hkns=false;
        for(int i=0;i<length;i++) {
            try {
                if (favvleg.getJSONObject(i).getString("bioguide_id").equals(obj.getString("bioguide_id"))) {
                    hkns = true;
                    //System.out.print("haiii");
                    //b4.setTag(2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(!hkns) {
            b4.setImageResource(R.drawable.star);
            //System.out.print("nai  haiii");
            // b4.setTag(1);
        }
        else
        {
            b4.setImageResource(R.drawable.starfull);
            //System.out.print("haiii");
        }




        //b4.setTag(1);
        final JSONObject finalObj = obj;
        b4.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v)
            {
                   int length = favvleg.length();

                boolean hkn=false; int pos = 0;
                for(int i=0;i<length;i++) {
                    try {
                        if (favvleg.getJSONObject(i).getString("bioguide_id").equals(finalObj.getString("bioguide_id"))) {
                            hkn = true;
                            pos=i;
                            //b4.setTag(2);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                        if(!hkn) {
                            System.out.println(finalObj);
                            favvleg.put(finalObj);
                            System.out.println("favv"+favvleg);
                            b4.setImageResource(R.drawable.starfull);
                           // b4.setTag(1);
                        }
                        else
                        {
                                favvleg.remove(pos);
                            b4.setImageResource(R.drawable.star);
                            System.out.println(favvleg);
                        }


                


        }});


    }

}

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


