package com.dscviit.vov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ClubDetails extends AppCompatActivity {

    public TextView name, description, activities, team, contact;
    public ImageView clubimageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        ActionBar actionBar=getSupportActionBar();

        name = findViewById(R.id.clubname);
        description = findViewById(R.id.clubdesc);
        activities = findViewById(R.id.clubactivities);
        team = findViewById(R.id.clubteam);
        contact = findViewById(R.id.clubcontact);
        clubimageview = findViewById(R.id.clubimage);

        if(getIntent().getStringExtra("Source").equals("ClubsAdapter"))
        {

            Intent intent = getIntent();
            final String clubname = intent.getStringExtra(ClubsAdapter.KEY_TITLE);
            final String clubdesc = intent.getStringExtra(ClubsAdapter.KEY_DESCRIPTION);
            final String clubactivities = intent.getStringExtra(ClubsAdapter.KEY_ACTIVITIES);
            final String clubteam = intent.getStringExtra(ClubsAdapter.KEY_TEAM);
            final String clubpoc = intent.getStringExtra(ClubsAdapter.KEY_CONTACT);
            String clubimage = intent.getStringExtra(ClubsAdapter.KEY_IMAGE);

            actionBar.setTitle(clubname);

            name.setText(clubname);
            description.setText(clubdesc);
            activities.setText(clubactivities);
            team.setText(clubteam);
            contact.setText(clubpoc);

            Picasso.get().load(clubimage).placeholder(R.color.Recyclerbg).into(clubimageview);

        }
        else if(getIntent().getStringExtra("Source").equals("Notification"))
        {

            Intent intent = getIntent();

            final String cname = intent.getStringExtra("Name");
            final String cdesc = intent.getStringExtra("Description");
            final String cactivities = intent.getStringExtra("Activities");
            final String cteam = intent.getStringExtra("Team");
            final String cpoc = intent.getStringExtra("POC");
            String cimage = intent.getStringExtra("Image");

            actionBar.setTitle(cname);

            name.setText(cname);
            description.setText(cdesc);
            activities.setText(cactivities);
            team.setText(cteam);
            contact.setText(cpoc);

            Picasso.get().load(cimage).placeholder(R.color.Recyclerbg).into(clubimageview);



        }







    }


}
