package com.example.threadexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Handler를 사용하여 UI 갱신을 하는 예
 */
public class MainActivity3 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    private Handler mHandler = new Handler();

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
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // UI 갱신
                            mTextView.setText(percent + "%");
                            mProgressBar.setProgress(percent);
                        }
                    });
                }
            }
        }).start();
    }
}
