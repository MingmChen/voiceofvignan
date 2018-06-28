package com.dscviit.vov;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setIcon(R.drawable.app_logo);



        if(!isNetworkAvailable())
        {
            Toast.makeText(getApplicationContext(),"You are offline! This app requires internet!!",Toast.LENGTH_LONG
            ).show();

            finish();
        }



    }



    public void FireEvents(View view) {
        Intent events = new Intent(getApplicationContext(), EventActivity.class);
        startActivity(events);
    }

    public void FireNews(View view) {

        Intent news = new Intent(this, NewsActivity.class);
        startActivity(news);
    }

    public void FireClubs(View view) {

        Intent clubs = new Intent(this, ClubActivity.class);
        startActivity(clubs);
    }


    public void FireAnnouncements(View view) {

        Intent announcements = new Intent(this, AnnouncementActivity.class);
        startActivity(announcements);
    }


    public void FireDev(View view) {
        Intent dev = new Intent(this, DeveloperZone.class);
        startActivity(dev);
    }


    public void FirePrefernces(View view) {

        startActivity(new Intent(this, UserProfile.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);

        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.shareapp) {
            onShareAction();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onShareAction() {
        String packagename=getPackageName();
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey! I love VoV and can say wow! " +
                "Checkout this app at https://play.google.com/store/apps/details?id="+packagename);
        startActivity(Intent.createChooser(shareIntent,"Share via"));

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
        }
        return activeNetworkInfo != null;
    }




}





