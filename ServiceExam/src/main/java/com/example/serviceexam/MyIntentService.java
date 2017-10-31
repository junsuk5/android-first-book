package com.example.serviceexam;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 5; i++) {
            try {
                // 1초 마다 쉬기
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // 1초 마다 로그 남기기
            Log.d("MyIntentService", "인텐트 서비스 동작 중 " + i);
        }
    }

}
