package com.example.test;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class dewormbroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        long[] vibrate = new long[]{0, 500, 1000, 1500};
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.dog);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context , "notifyLemubit")
                .setSmallIcon(R.drawable.mite_icon)
                .setContentTitle("iPet")
                .setContentText("今天要幫狗狗驅蟲喔")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(uri);
//                .setVibrate(vibrate);
        Random random = new Random();
        int number = random.nextInt(1000);
        System.out.println(number);
        Notification notification = builder.build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManagerCompat nos = NotificationManagerCompat.from(context);
        nos.notify(number,builder.build());
    }

}
