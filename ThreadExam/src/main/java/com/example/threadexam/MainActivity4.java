package com.example.threadexam;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 잘 동작하지만 잘못된 예, 메모리 릭 발생
 */
public class MainActivity4 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int percent = msg.arg1;
            // UI 갱신
            mTextView.setText(percent + "%");
            mProgressBar.setProgress(percent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }

    public void download(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 오래 걸리는 일
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int percent = i;
                    Message message = Message.obtain();
                    message.arg1 = percent;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }
}
