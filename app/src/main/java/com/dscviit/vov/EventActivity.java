package com.dscviit.vov;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
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

public class EventActivity extends AppCompatActivity {


    private static final String TAG= EventActivity.class.getSimpleName();
    private RecyclerView recyclerViewEvents;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Events> allEvents;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        progressBar=findViewById(R.id.progressBar_cyclic);




        allEvents=new ArrayList<>();
        recyclerViewEvents=findViewById(R.id.recyclerviewEvents);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewEvents.setLayoutManager(linearLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Events");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Events events=dataSnapshot.getValue(Events.class);
                allEvents.add(events);



                // stop animating Shimmer and hide the layout
                progressBar.setVisibility(View.INVISIBLE);




            adapter=new EventsAdapter(EventActivity.this,allEvents);
                recyclerViewEvents.setAdapter(adapter);

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







