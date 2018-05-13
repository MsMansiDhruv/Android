package com.example.nemesis.localnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by Nemesis on 2/11/2018.
 */

public class NotificationHelper {

    Context context;
    NotificationManager manager;
    NotificationCompat.Builder builder;
    static int notificationId = 0;

    NotificationHelper(Context context){
        this.context = context;

        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("MyChannel","Demo Channel",NotificationManager.IMPORTANCE_DEFAULT));
        }

    }

    void createNotification(){
        notificationId++;
        Notification n = builder.build();
        manager.notify(notificationId,n);
    }

    void setBuilder(String title,String message){
        builder = new NotificationCompat.Builder(context,"MyChannel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message);
    }

    void Type1Notification(String title,String message){
        setBuilder(title, message);
        createNotification();
    }

    void Type2Notification(String title,String message){
        setBuilder(title,message);

        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();
        inbox.setBigContentTitle("Big box");
        inbox.addLine("This is line 1 ");
        inbox.addLine("This is line 2");
        inbox.setBuilder(builder);

        createNotification();
    }

    void Type3Notification(String title,String message){
        setBuilder(title,message);

        Intent i1 = new Intent(context,SecondActivity.class);
        i1.setAction("Yes");

        PendingIntent pi1 = PendingIntent.getActivity(context,1,i1,0);
        builder.addAction(R.drawable.ic_notification,"Yes",pi1);


        createNotification();
    }

    void CustomNotification(String title,String message) {

        setBuilder(title,message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_notification);
            remoteViews.setTextViewText(R.id.textViewTitle, title);
            remoteViews.setImageViewResource(R.id.imageView, R.drawable.sierra);
            builder.setContent(remoteViews);

            builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        }

        createNotification();

    }
}