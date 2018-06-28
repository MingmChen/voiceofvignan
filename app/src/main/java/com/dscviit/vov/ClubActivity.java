package com.dscviit.vov;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class ClubActivity extends AppCompatActivity {


    private static final String TAG = ClubActivity.class.getSimpleName();
    private RecyclerView recyclerViewClubs;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Clubs> allClubs;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);

        progressBar=findViewById(R.id.progressBar_cyclic);
        allClubs = new ArrayList<Clubs>();
        recyclerViewClubs = findViewById(R.id.recyclerviewclubs);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewClubs.setLayoutManager(linearLayoutManager);

        progressBar.setVisibility(View.VISIBLE);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("StudentClubs");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Clubs clubs = dataSnapshot.getValue(Clubs.class);
                allClubs.add(clubs);

               progressBar.setVisibility(View.INVISIBLE);

                adapter = new ClubsAdapter(ClubActivity.this, allClubs);
                recyclerViewClubs.setAdapter(adapter);

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







