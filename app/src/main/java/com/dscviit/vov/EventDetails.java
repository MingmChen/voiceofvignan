package com.dscviit.vov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDetails extends AppCompatActivity {

    public TextView name, description, date, venue, cn,cd,ce,time,category,relevance;
    public ImageView eventimageview;
    public Button regbtn;
    public String eventreg;

    public static final String KEY_WEB = "reglink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);


        name = findViewById(R.id.eventname);
        description = findViewById(R.id.eventdesc);
        date = findViewById(R.id.eventdate);
        time=findViewById(R.id.eventtime);
        venue = findViewById(R.id.eventvenue);
        cn = findViewById(R.id.eventcname);
        cd=findViewById(R.id.eventcdesc);
        ce=findViewById(R.id.eventcval);
        relevance=findViewById(R.id.eventRelevance);
        eventimageview = findViewById(R.id.eventimage);
        category=findViewById(R.id.eventCategory);
        regbtn=findViewById(R.id.regbtn);


        if(getIntent().getStringExtra("Source").equals("EventsAdapter"))
        {


            Intent intent = getIntent();
            final String eventname = intent.getStringExtra(EventsAdapter.KEY_TITLE);
            final String eventdesc = intent.getStringExtra(EventsAdapter.KEY_DESCRIPTION);
            final String eventDate=intent.getStringExtra(EventsAdapter.KEY_DATE);
            final String eventtime = intent.getStringExtra(EventsAdapter.KEY_TIME);
            final String eventvenue = intent.getStringExtra(EventsAdapter.KEY_VENUE);
            final String eventcn= intent.getStringExtra(EventsAdapter.KEY_CN);
            final String eventcd= intent.getStringExtra(EventsAdapter.KEY_CD);
            final String eventce= intent.getStringExtra(EventsAdapter.KEY_CE);
             eventreg= intent.getStringExtra(EventsAdapter.KEY_REG);
            final String eventcategory=intent.getStringExtra(EventsAdapter.KEY_CATEGORY);
            final String eventrelevance=intent.getStringExtra(EventsAdapter.KEY_RELEVANCE);
            final String eventregbtndesc=intent.getStringExtra(EventsAdapter.KEY_REGBTNDESC);



            String eventimage= intent.getStringExtra(EventsAdapter.KEY_IMAGE);
            actionBar.setTitle(eventname);


            name.setText(eventname);
            description.setText(eventdesc);
            date.setText(eventDate);
            time.setText(eventtime);
            venue.setText(eventvenue);
            cn.setText(eventcn);
            cd.setText(eventcd);
            ce.setText(eventce);
            category.setText(eventcategory);
            time.setText(eventtime);
            relevance.setText(eventrelevance);
            Picasso.get().load(eventimage).placeholder(R.color.Recyclerbg).into(eventimageview);
            regbtn.setText(eventregbtndesc);

        }
        else  if(getIntent().getStringExtra("Source").equals("Notification"))
        {
            Intent intent=getIntent();


            final String  ename = intent.getStringExtra("Name");
            final String edescription= intent.getStringExtra("Description");
            final String ecategory= intent.getStringExtra("Category");
            final String ecd= intent.getStringExtra("CD");
            final String ecn = intent.getStringExtra("CN");
            final String ece= intent.getStringExtra("CE");
            final String edate= intent.getStringExtra("Date");
            final String eposteddate= intent.getStringExtra("PostedDate");
             eventreg= intent.getStringExtra("Reg");
            final String etime= intent.getStringExtra("Time");
            final String evenue= intent.getStringExtra("Venue");

            String eimage = intent.getStringExtra("Image");

            actionBar.setTitle(ename);


            name.setText(ename);
            description.setText(edescription);
            date.setText(edate);
            venue.setText(evenue);
            cn.setText(ecn);
            cd.setText(ecd);
            ce.setText(ece);
            category.setText(ecategory);
            time.setText(etime);
            Picasso.get().load(eimage).placeholder(R.color.Recyclerbg).into(eventimageview);

        }






            regbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url=eventreg;

                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                    Intent webBrowser = new Intent(EventDetails.this,InAppBrowser.class);
                    webBrowser.putExtra(KEY_WEB,url);
                    startActivity(webBrowser);

                }
            });

        }

}

