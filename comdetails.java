package com.example.yogi.myapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.yogi.myapplication.MainActivity.favvcom;

public class comdetails extends AppCompatActivity {

    Context context;

    public comdetails()
    {
        context = this;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comdetails);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        String detc = getIntent().getExtras().getString("cdet");
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(detc);
            System.out.println(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView t1 = (TextView) findViewById(R.id.commmid);
        try {
            String b;
            if(obj.has("committee_id"))
            {
                b = obj.getString("committee_id");
                if(b == "null")
                    b="N.A.";
                t1.setText(b);
            }
            else { t1.setText("N.A.");}

        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.commmname);
        try {
            String b;
            if (obj.has("name"))
            {
                b = obj.getString("name");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else { t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.commmchamber);
        ImageView i1 = (ImageView)findViewById(R.id.commmchimg);
        try {
            String b = obj.getString("chamber");
            if(b.equals("house"))
            {
                b = "House";
                i1.setImageResource(R.drawable.h);
            }

            else {
                i1.setImageResource(R.drawable.s);
                if(b.equals("senate"))
                {b = "Senate";}
                else
                {b = "Joint";}
            }

            t1.setText(b);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.commmparcom);
        try {
            String b;
            if(obj.has("parent_committee_id")) {
                b = obj.getString("parent_committee_id");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else { t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.commmcontact);
        try {
        String b;
        if(obj.has("phone"))
        {
            b = obj.getString("phone");
            if (b == "null")
                b = "N.A.";
            t1.setText(b);
        }
        else { t1.setText("N.A.");}
    } catch (JSONException e) {
        e.printStackTrace();
    }

        t1 = (TextView) findViewById(R.id.commmoffice);
        try {
            String b;
            if(obj.has("office"))
            {
                b = obj.getString("office");
                if (b == "null")
                    b = "N.A.";
                t1.setText(b);
            }
            else { t1.setText("N.A.");}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ImageButton b6=(ImageButton) findViewById(R.id.starc);
        int length = favvcom.length();
        boolean hknc=false;
        for(int i=0;i<length;i++) {
            try {
                if (favvcom.getJSONObject(i).getString("committee_id").equals(obj.getString("committee_id"))) {
                    hknc = true;
                    //System.out.print("haiii");
                    //b4.setTag(2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(!hknc) {
            b6.setImageResource(R.drawable.star);
            //System.out.print("nai  haiii");
            // b4.setTag(1);
        }
        else
        {
            b6.setImageResource(R.drawable.starfull);
            //System.out.print("haiii");
        }

        final JSONObject finalObj = obj;
        b6.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {

                int length = favvcom.length();

                boolean hknc=false; int pos = 0;
                for(int i=0;i<length;i++) {
                    try {
                        if (favvcom.getJSONObject(i).getString("committee_id").equals(finalObj.getString("committee_id"))) {
                            hknc = true;
                            pos=i;
                            //b4.setTag(2);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(!hknc) {
                    System.out.println("Ye nai HAI!!!"+finalObj);
                    favvcom.put(finalObj);
                    //System.out.println("favv"+favvleg);
                    b6.setImageResource(R.drawable.starfull);
                    // b4.setTag(1);
                }
                else
                {
                    favvcom.remove(pos);
                    b6.setImageResource(R.drawable.star);
                    //System.out.println(favvleg);
                }


            }


        });


                System.out.println("favvcom"+favvcom);

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
}
