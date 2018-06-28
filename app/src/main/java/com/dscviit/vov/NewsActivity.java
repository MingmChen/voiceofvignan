package com.dscviit.vov;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {


    RecyclerView recyclerViewNews;
    List<News> newsList = new ArrayList<News>();
    private LinearLayoutManager linearLayoutManager;
    MyNewsAdapter adapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar=findViewById(R.id.progressBar_cyclic);


        recyclerViewNews = findViewById(R.id.recyclerview1);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewNews.setLayoutManager(linearLayoutManager);
        adapter = new MyNewsAdapter(this,newsList);

        progressBar.setVisibility(View.VISIBLE);


        recyclerViewNews.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNews.setItemAnimator(new DefaultItemAnimator());


        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Campus News");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                News news=dataSnapshot.getValue(News.class);
                newsList.add(news);

                progressBar.setVisibility(View.INVISIBLE);

                adapter=new MyNewsAdapter(NewsActivity.this,newsList);
                recyclerViewNews.setAdapter(adapter);
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





