package com.dscviit.vov;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    String photoUrl,name,email;

    Switch eventswitch,clubswitch,newsswitch,annswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        final SharedPreferences preferences=getPreferences(MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setTitle("Profile");

        eventswitch=findViewById(R.id.eventswitch);
        annswitch=findViewById(R.id.announcementswitch);
        clubswitch=findViewById(R.id.clubsswitch);
        newsswitch=findViewById(R.id.newsswitch);


        boolean eventstatus = preferences.getBoolean("eventswitchpref",false);  //default is true
        if (eventstatus) //if (tgpref) may be enough, not sure
        {
            eventswitch.setChecked(true);
        }
        else
        {
            eventswitch.setChecked(false);
        }

        boolean clubstatus = preferences.getBoolean("clubswitchpref",false);  //default is true
        if (clubstatus) //if (tgpref) may be enough, not sure
        {
            clubswitch.setChecked(true);
        }
        else
        {
            clubswitch.setChecked(false);
        }


        boolean newsstatus = preferences.getBoolean("newsswitchpref", false);  //default is true
        if (newsstatus) //if (tgpref) may be enough, not sure
        {
            newsswitch.setChecked(true);
        }
        else
        {
            newsswitch.setChecked(false);
        }

        boolean annstatus = preferences.getBoolean("annswitchpref", false);  //default is true
        if (annstatus) //if (tgpref) may be enough, not sure
        {
            annswitch.setChecked(true);
        }
        else
        {
            annswitch.setChecked(false);
        }

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null)
        {

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                 name = profile.getDisplayName();
                 email = profile.getEmail();
                Uri photoUri = profile.getPhotoUrl();

                if(photoUri!=null)
                photoUrl=photoUri.toString();


            };
        }



        CircleImageView userPhoto=(CircleImageView) findViewById(R.id.userphoto);
        TextView userName=(TextView)findViewById(R.id.userName);
        TextView userEmail=(TextView)findViewById(R.id.userEmail);

        if(photoUrl!=null)
        {
            Picasso.get().load(photoUrl).placeholder(R.drawable.userprofile).into(userPhoto);

        }
        else {


            Picasso.get().load(R.drawable.userprofile).noFade().placeholder(R.color.placeholder_bg).into(userPhoto);

        }

        userName.setText(name);
        userEmail.setText(email);

        ImageButton signout=(ImageButton) findViewById(R.id.btnlogout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthUI.getInstance().
                        signOut(UserProfile.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(UserProfile.this,"Signed out successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserProfile.this,LoginActivity.class));
                                finish();
                            }
                        });


                }
        });



        eventswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eventswitch.isChecked())
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("EventsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("eventswitchpref", true); // value to store
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(),"Subscribed to Events",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot subscribe right now!",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("eventswitchpref", false); // value to store
                                    editor.apply();
                                    eventswitch.setChecked(false);



                                }
                            });
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("EventAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Unsubscribed to Events",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("eventswitchpref", false); // value to store
                                    editor.apply();
                                    eventswitch.setChecked(false);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot unsubscribe right now!",Toast.LENGTH_SHORT).show();


                                }
                            });

                }


            }
        });


        clubswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clubswitch.isChecked())
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("ClubsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("clubswitchpref", true); // value to store
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(),"Subscribed to Clubs",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot subscribe right now!",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("clubswitchpref", false); // value to store
                                    editor.apply();
                                    clubswitch.setChecked(false);


                                }
                            });
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ClubsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Unsubscribed to Clubs",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("clubswitchpref", false); // value to store
                                    editor.apply();
                                    clubswitch.setChecked(false);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot unsubscribe right now!",Toast.LENGTH_SHORT).show();


                                }
                            });

                }


            }
        });

        newsswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newsswitch.isChecked())
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("NewsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("newsswitchpref", true); // value to store
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(),"Subscribed to News",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot subscribe right now!",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("newsswitchpref", false); // value to store
                                    editor.apply();
                                    newsswitch.setChecked(false);


                                }
                            });
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("NewsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Unsubscribed to News",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("newsswitchpref", false); // value to store
                                    editor.apply();
                                    newsswitch.setChecked(false);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot unsubscribe right now!",Toast.LENGTH_SHORT).show();


                                }
                            });

                }



            }
        });

        annswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(annswitch.isChecked())
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("AnnouncementsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("annswitchpref", true); // value to store
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(),"Subscribed to Announcements",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("annswitchpref", false); // value to store
                                    editor.apply();
                                    annswitch.setChecked(false);
                                    Toast.makeText(getApplicationContext(),"Cannot subscribe right now!",Toast.LENGTH_SHORT).show();


                                }
                            });
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("AnnouncementsAlerts")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("annswitchpref", false); // value to store
                                    editor.apply();
                                    annswitch.setChecked(false);
                                    Toast.makeText(getApplicationContext(),"Unsubscribed to Announcements",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Cannot unsubscribe right now!",Toast.LENGTH_SHORT).show();


                                }
                            });

                }


            }
        });

    }
}
