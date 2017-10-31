package com.example.notificationexam;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // 필수 항목
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");

        // 액션 정의
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // 클릭 이벤트 설정
        builder.setContentIntent(pendingIntent);

        // 큰 아이콘 설정
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);

        // 색상 변경
        builder.setColor(Color.RED);

        // 기본 알림음 사운드 설정
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        // 진동설정: 대기시간, 진동시간, 대기시간, 진동시간 ... 반복 패턴
        long[] vibrate = {0, 100, 200, 300};
        builder.setVibrate(vibrate);


        builder.setAutoCancel(true);
        // 시스템에 알림을 통지
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        // 알림 통지
        manager.notify(1, builder.build());
    }

    private void hide() {
        // 알림 해제
        NotificationManagerCompat.from(this).cancel(1);
    }


    public void createNotification(View view) {
        show();
    }

    public void removeNotification(View view) {
        hide();
    }

}
