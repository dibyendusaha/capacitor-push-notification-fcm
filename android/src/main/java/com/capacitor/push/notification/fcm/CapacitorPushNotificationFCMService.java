package com.capacitor.push.notification.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

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
            notificationBuilder.setPriority(remoteMessage.getPriority());
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle());
            notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
            notificationBuilder.setColor(getResources().getColor(R.color.notification_color));
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon));
            notificationBuilder.setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        }
    }
}
