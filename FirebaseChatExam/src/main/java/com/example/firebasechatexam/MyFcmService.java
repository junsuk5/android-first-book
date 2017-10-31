package com.example.firebasechatexam;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by junsuk on 2017. 8. 28..
 */

public class MyFcmService extends FirebaseMessagingService {
    public static final String TAG = MyFcmService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // 여기서 FCM 메시지를 처리 함
        Log.d(TAG, "onMessageReceived ID: " + remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived DATA: " + remoteMessage.getData());
    }
}
