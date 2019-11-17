package com.example.quemtocahoje.Notificação;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "mFirebaseIIDService";
    private static final String SUBSCRIBE_TO = "userABC";

    @Override
    public void onTokenRefresh() {
        /*
          This method is invoked whenever the token refreshes
          OPTIONAL: If you want to send messages to this application instance
          or manage this apps subscriptions on the server side,
          you can send this token to your server.
        */
        SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token.edit().putString("novoToken", FirebaseInstanceId.getInstance().getToken()).apply();

        // Once the token is generated, subscribe to topic with the userId
        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
        Log.d(TAG, "onTokenRefresh completed with token: " + token);
    }
}