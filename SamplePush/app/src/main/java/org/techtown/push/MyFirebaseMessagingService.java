package org.techtown.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    private String msg,title;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(String token){
        super.onNewToken(token);
        Log.e(TAG, "onNewToken called: " + token);

       // sendRegistrationToServer(token); // Send Token to Server
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived called : ");

        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        String contents = data.get("contents");

        if(contents == null) {
            title = remoteMessage.getNotification().getTitle();
            msg = remoteMessage.getNotification().getBody();
            sendNotification(remoteMessage.getData().get("message"));
        } else {
            sendToActivity(getApplicationContext(), from, contents);
        }
    }

    private void sendToActivity(Context context, String from, String contents) { // Message processing
        Log.d(TAG, "from : " + from + ", contents : " + contents);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    private void sendNotification(String messageBody) {         // Notification processing
        Log.d(TAG, "messageBody : " + messageBody);
        Log.d(TAG, "title : " + title);
        Log.d(TAG, "msg : " + msg);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)//Where push up, android icon build
                .setContentTitle("FCM Push Test")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1,1000}); // during 1000msc(1sc) vibrate

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
        mBuilder.setContentIntent(contentIntent);
    }
}



/* private void sendRegistrationToServer(String token) {
        // Send Token to Server

    }*/

