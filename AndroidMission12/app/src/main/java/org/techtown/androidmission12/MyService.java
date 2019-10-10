package org.techtown.androidmission12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() { }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
    }

    private void processCommand(Intent intent)

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
