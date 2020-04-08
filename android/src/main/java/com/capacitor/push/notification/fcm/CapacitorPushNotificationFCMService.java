package com.capacitor.push.notification.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.capacitor.push.notification.fcm.capacitorpushnotificationfcm.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class CapacitorPushNotificationFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() == null && remoteMessage.getData().size() > 0) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id");
            notificationBuilder.setContentTitle(remoteMessage.getData().get("title"));
            notificationBuilder.setContentText(remoteMessage.getData().get("message"));
            notificationBuilder.setColor(Color.parseColor(remoteMessage.getData().get("color")));
            notificationBuilder.setLargeIcon(BitmapFactory.decodeFile(remoteMessage.getData().get("largeIcon")));
            notificationBuilder.setPriority(remoteMessage.getPriority());
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle());
            notificationBuilder.setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        }
    }
}
