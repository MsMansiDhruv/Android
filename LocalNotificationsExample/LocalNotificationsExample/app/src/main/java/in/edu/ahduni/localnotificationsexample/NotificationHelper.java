package in.edu.ahduni.localnotificationsexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.widget.RemoteViews;

/**
 * Created by macbookair on 01/02/18.
 */

public class NotificationHelper {

    Context context;
    NotificationManager manager;
    NotificationCompat.Builder builder;
    static int notificationId = 0;
    int flag = 0;

    NotificationHelper(Context context) {
        this.context = context;

        manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel
                            ("MyChannel", "Demo Channel",
                                    NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    void createNotification() {
        Notification n = builder.build();

        if (flag != 1)
            notificationId++;

        manager.notify(notificationId, n);
    }

    void setBuilder(String title, String message) {

        builder = new NotificationCompat.Builder
                (context, "MyChannel")
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(title)
                        .setContentText(message);
    }

    void displayType1Notification(String title, String message) {
        setBuilder(title, message);
        createNotification();
    }

    void displayType2Notification(String title, String message) {
        setBuilder(title, message);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Expanded Inbox");
        inboxStyle.addLine("You have received 2 emails");
        inboxStyle.addLine("Email From Tom");
        inboxStyle.addLine("Email From Jerry");

        builder.setStyle(inboxStyle);

        createNotification();
    }

    void displayType3Notification(String title, String message) {
        setBuilder(title, message);

        Intent i = new Intent(context, SecondActivity.class);
        //Name of Action That Would Be Passed On To The Second Activity
        i.setAction("I'Fine");

        PendingIntent pi = PendingIntent.getActivity
                (context, 1, i, 0);
        builder.addAction(R.mipmap.ic_launcher, "I'm Fine", pi);

        //Button-2
        Intent i2 = new Intent(context, SecondActivity.class);
        i2.setAction("I'm Not Good");
        PendingIntent pi2 = PendingIntent.getActivity
                (context, 2, i2, 0);
        builder.addAction(R.mipmap.ic_launcher, "Not Good", pi2);

        createNotification();
    }

    void displayCustomNotification(String title, String message) {
        setBuilder(title, message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.custom_notification);

            remoteViews.setTextViewText(R.id.txtTitle, title);
            remoteViews.setTextViewText(R.id.txtMessage, message);
            remoteViews.setImageViewResource(R.id.imgPoster, R.drawable.sierra);

            builder.setContent(remoteViews);

            //Compulsory attribute for custom notification
            builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        }
        createNotification();
    }

    void clearNotification() {

        if (manager != null) {
            notificationId = 0;
            manager.cancelAll();
            /*for(int i=1; i<=notificationId; i++) {
            manager.cancel(i);
            }*/
        }
    }

    void displayInlineTextboxNotification(String title, String message) {
        setBuilder(title, message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {

            RemoteInput remoteInput =
                    new RemoteInput.Builder("edtReply")
                            .setLabel("Enter Reply Message").build();

            Intent i = new Intent(context, SecondActivity.class);
            PendingIntent pendingIntent = PendingIntent
                    .getActivity(context, 1, i, 0);

            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder
                    (R.drawable.ic_send, "Reply", pendingIntent)
                            .addRemoteInput(remoteInput).build();

            builder.addAction(action);
        }

        createNotification();
    }

    void displayReplyMessageNotification(String message) {
        flag = 1;
        setBuilder("Message Sent !", message);
        createNotification();
    }
}
