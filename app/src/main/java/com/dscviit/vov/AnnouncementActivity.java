package com.dscviit.vov;

import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementActivity extends AppCompatActivity {


    private static final String TAG = AnnouncementActivity.class.getSimpleName();
    private RecyclerView recyclerViewAnnouncements;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Announcements> allAnnouncements;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setTitle("Announcements");

        progressBar=findViewById(R.id.progressBar_cyclic);
        allAnnouncements = new ArrayList<Announcements>();
        recyclerViewAnnouncements = findViewById(R.id.recyclerviewAnnouncements);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewAnnouncements.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);




        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Announcements");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Announcements announcements = dataSnapshot.getValue(Announcements.class);
                allAnnouncements.add(announcements);


                progressBar.setVisibility(View.INVISIBLE);

                adapter = new AnnouncementsAdapter(AnnouncementActivity.this, allAnnouncements);
                recyclerViewAnnouncements.setAdapter(adapter);


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}







