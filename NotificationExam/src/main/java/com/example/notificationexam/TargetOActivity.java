package com.example.notificationexam;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TargetOActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_o);
    }

    private void show() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification.Builder builder = new Notification.Builder(this);

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

        // 색상 설정
        builder.setColor(Color.RED);

        // 기본 알림음 사운드 설정
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        // 알림 터치시 알림 자동 취소
        builder.setAutoCancel(true);

        // 시스템에 알림을 통지
//        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // 채널 생성
            NotificationChannel channel = new NotificationChannel("my_channel", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("채널 설명");
            channel.enableLights(true);

            // 기기가 지원하면 기기에서 깜빡이는 불 빛 색을 지정
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            manager.createNotificationChannel(channel);

            // 알림에 채널 ID를 설정
            builder.setChannelId("my_channel");
        }

        // 알림 통지
        manager.notify(1, builder.build());
    }

    private void hide() {
        // 알림 해제
//        NotificationManagerCompat.from(this).cancel(1);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(1);
    }

    public void createNotification(View view) {
        show();
    }

    public void removeNotification(View view) {
        hide();
    }
}
