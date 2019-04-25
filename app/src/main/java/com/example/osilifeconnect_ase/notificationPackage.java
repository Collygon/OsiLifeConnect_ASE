package com.example.osilifeconnect_ase;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Date;

public class notificationPackage {

    private static notificationPackage instance = null;
    private static NotificationCompat.Builder nBuilder;
    private static String CHANNEL_ID = "notifyChannel";

    public static notificationPackage getInstance() {
        if(instance == null)
            instance = new notificationPackage();
        return instance;
    }

    public static void loginNotify(Context c){
        Intent intent = new Intent(c, dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);

        nBuilder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.osiLogo)
                .setContentTitle("Osilife Connect: Login")
                .setContentText("You have successfully logged in!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, nBuilder.build());
    }

    public static void weightBTNotify(Context c){
        Intent intent = new Intent(c, dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);

        nBuilder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.osiLogo)
                .setContentTitle("Osilife Connect: Weight Sensor")
                .setContentText("Weight Sensor is now connected via bluetooth")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, nBuilder.build());
    }

    public static void weightReadNotify(Context c, String lw, String kw, Date d){
        Intent intent = new Intent(c, dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);

        nBuilder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.osiLogo)
                .setContentTitle("Osilife Connect: Weight Sensor")
                .setContentText("Weight taken successfully!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Weight (lbs): " + lw +
                                "\nWeight (kgs): " + kw +
                                "\nDate: " + d))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, nBuilder.build());
    }

    public static void bloodBTNotify(Context c){
        Intent intent = new Intent(c, dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);

        nBuilder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.osiLogo)
                .setContentTitle("Osilife Connect: Blood Pressure Monitor")
                .setContentText("Blood Pressure Monitor is now connected via bluetooth")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, nBuilder.build());
    }

    public static void bloodReadNotify(Context c, double dyn, double sys, int pr, Date d){
        Intent intent = new Intent(c, dashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);

        nBuilder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.osiLogo)
                .setContentTitle("Osilife Connect: Blood Pressure Monitor")
                .setContentText("Blood Pressure read successfully!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Dynostolic: " + dyn +
                                "\nSystolic: " + sys +
                                "\nPulse Rate: " + pr +
                                "\nDate: " + d))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, nBuilder.build());
    }

    public static String getChannelId() {
        return CHANNEL_ID;
    }
}
