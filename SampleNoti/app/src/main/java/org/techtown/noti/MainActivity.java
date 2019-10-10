/*
*
* error detecting
*
* */

package org.techtown.noti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.techtown.noti.R;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showNoti1();
            }
        });
    }

    public void showNoti1() {
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.0) {
            if(manager.getNotificationChannel(CHANNEL_ID) != null) {
                manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT));
                builder = new NotificationCompat.Builder(this,CHANNEL_ID);
            }
        }
        else {
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        }
        builder.setContentTitle("Simple notification");
        builder.setContentText("This is Notification Message");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification noti = builder.build();

        manager.notify(1, noti);
    }
}
