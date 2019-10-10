package org.techtown.push;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, token);
        sendRegistrationToServer(token);

    }


    private void sendRegistrationToServer(String token) {
        //Using where send token to app server
    }
}
