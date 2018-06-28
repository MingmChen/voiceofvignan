package com.dscviit.vov;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


/**
 * Created by techpathi on 16/6/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessagingServce";
    Bitmap bitmap;
    NotificationManager notificationManager;
    private String ADMIN_CHANNEL_ID = "VoV";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().get("activity").equals("Clubs")) {

            Intent ClubnotificationIntent = new Intent(this, ClubDetails.class);
            ClubnotificationIntent.putExtra("ActivityName", remoteMessage.getData().get("activity"));
            ClubnotificationIntent.putExtra("Name", remoteMessage.getData().get("cname"));
            ClubnotificationIntent.putExtra("Description", remoteMessage.getData().get("cdescription"));
            ClubnotificationIntent.putExtra("Activities", remoteMessage.getData().get("cactivities"));
            ClubnotificationIntent.putExtra("Image", remoteMessage.getData().get("cimage"));
            ClubnotificationIntent.putExtra("POC", remoteMessage.getData().get("cpoc"));
            ClubnotificationIntent.putExtra("Team", remoteMessage.getData().get("cteam"));
            ClubnotificationIntent.putExtra("Source", "Notification");

            ClubnotificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            final PendingIntent ClubpendingIntent = PendingIntent.getActivity(this, 0, ClubnotificationIntent,
                    PendingIntent.FLAG_ONE_SHOT);


            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //Setting up Notification channels for android O and above
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                setupChannels();
            }
            int notificationId = new Random().nextInt(60000);

            Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("cimage")); //obtain the image


            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)  //a resource for your custom small icon
                    .setContentTitle(remoteMessage.getData().get("cname")) //the "title" value you sent in your notification
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .setSummaryText(remoteMessage.getData().get("cdescription"))
                            .bigPicture(bitmap))
                    .setContentText(remoteMessage.getData().get("cdescription")) //ditto
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setSound(defaultSoundUri)
                    .setContentIntent(ClubpendingIntent);


            notificationManager.notify(notificationId, notificationBuilder.build());

        } else if (remoteMessage.getData().get("activity").equals("News")) {

            Intent NewsnotificationIntent = new Intent(this, NewsDetails.class);
            NewsnotificationIntent.putExtra("ActivityName", remoteMessage.getData().get("activity"));
            NewsnotificationIntent.putExtra("Headline", remoteMessage.getData().get("nheadline"));
            NewsnotificationIntent.putExtra("Content", remoteMessage.getData().get("ncontent"));
            NewsnotificationIntent.putExtra("Category", remoteMessage.getData().get("ncategory"));
            NewsnotificationIntent.putExtra("Image", remoteMessage.getData().get("nimage"));
            NewsnotificationIntent.putExtra("PostedDate", remoteMessage.getData().get("nposteddate"));
            NewsnotificationIntent.putExtra("PostedBy", remoteMessage.getData().get("npostedby"));
            NewsnotificationIntent.putExtra("Source", "Notification");

            NewsnotificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            final PendingIntent NewspendingIntent = PendingIntent.getActivity(this, 0, NewsnotificationIntent,
                    PendingIntent.FLAG_ONE_SHOT);


            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //Setting up Notification channels for android O and above
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                setupChannels();
            }
            int notificationId = new Random().nextInt(60000);

            Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("nimage")); //obtain the image


            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)  //a resource for your custom small icon
                    .setContentTitle(remoteMessage.getData().get("ncategory")) //the "title" value you sent in your notification
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .setSummaryText(remoteMessage.getData().get("cheadline"))
                            .bigPicture(bitmap))
                    .setContentText(remoteMessage.getData().get("nheadline")) //ditto
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setSound(defaultSoundUri)
                    .setContentIntent(NewspendingIntent);


            notificationManager.notify(notificationId, notificationBuilder.build());

        }
     else if (remoteMessage.getData().get("activity").equals("Events")) {

        Intent EventnotificationIntent = new Intent(this, EventDetails.class);
        EventnotificationIntent.putExtra("ActivityName", remoteMessage.getData().get("activity"));
       EventnotificationIntent.putExtra("Name", remoteMessage.getData().get("ename"));
            EventnotificationIntent.putExtra("Description", remoteMessage.getData().get("edescription"));
            EventnotificationIntent.putExtra("Image", remoteMessage.getData().get("eimage"));
            EventnotificationIntent.putExtra("Category", remoteMessage.getData().get("ecategory"));
            EventnotificationIntent.putExtra("CD", remoteMessage.getData().get("ecd"));
            EventnotificationIntent.putExtra("CN", remoteMessage.getData().get("ecn"));
            EventnotificationIntent.putExtra("CE", remoteMessage.getData().get("ece"));
            EventnotificationIntent.putExtra("Date", remoteMessage.getData().get("edate"));
            EventnotificationIntent.putExtra("PostedDate", remoteMessage.getData().get("epostedAt"));
            EventnotificationIntent.putExtra("Reg", remoteMessage.getData().get("ereg"));
            EventnotificationIntent.putExtra("Time", remoteMessage.getData().get("etime"));
            EventnotificationIntent.putExtra("Venue", remoteMessage.getData().get("evenue"));EventnotificationIntent.putExtra("Source", "Notification");

        EventnotificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent EventpendingIntent = PendingIntent.getActivity(this, 0, EventnotificationIntent,
                PendingIntent.FLAG_ONE_SHOT);


        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels();
        }
        int notificationId = new Random().nextInt(60000);

        Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("eimage")); //obtain the image


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("ename")) //the "title" value you sent in your notification
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .setSummaryText(remoteMessage.getData().get("edescription"))
                        .bigPicture(bitmap))
                .setContentText(remoteMessage.getData().get("edescription")) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setSound(defaultSoundUri)
                .setContentIntent(EventpendingIntent);


        notificationManager.notify(notificationId, notificationBuilder.build());

    }
    else if(remoteMessage.getData().get("activity").equals("Announcements"))
        {

            Intent AnnouncementnotificationIntent = new Intent(this, AnnouncementActivity.class);

            AnnouncementnotificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            final PendingIntent AnnpendingIntent = PendingIntent.getActivity(this, 0, AnnouncementnotificationIntent,
                    PendingIntent.FLAG_ONE_SHOT);


            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //Setting up Notification channels for android O and above
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                setupChannels();
            }
            int notificationId = new Random().nextInt(60000);

            Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("aimage")); //obtain the image


            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)  //a resource for your custom small icon
                    .setContentTitle(remoteMessage.getData().get("acategory")) //the "title" value you sent in your notification
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .setSummaryText(remoteMessage.getData().get("adescription"))
                            .bigPicture(bitmap))
                    .setContentText(remoteMessage.getData().get("adescription")) //ditto
                    .setAutoCancel(true)  //dismisses the notification on click
                    .setSound(defaultSoundUri)
                    .setContentIntent(AnnpendingIntent);


            notificationManager.notify(notificationId, notificationBuilder.build());

        }

    }

    private Bitmap getBitmapfromUrl(String image) {

        try {
            URL url = new URL(image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels() {
        CharSequence adminChannelName = "VoV";
        String adminChannelDescription = "Voice of Vignan ";

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }

    }
}
