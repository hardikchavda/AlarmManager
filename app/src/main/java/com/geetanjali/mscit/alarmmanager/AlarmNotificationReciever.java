package com.geetanjali.mscit.alarmmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


public class AlarmNotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID); //For > API26 (OREO)
        } else {
            builder = new Notification.Builder(context);
        }

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Alarm Activated")
                .setContentText("This is my Alarm")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentInfo("info");

        NotificationManager notify = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //For Oreo only
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notify.createNotificationChannel(channel);
        }
        //Oreo code ends
        notify.notify(notifyID, builder.build());
    }
}
