package com.example.threadexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 잘못된 사용 방법
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }

    public void download(View view) throws InterruptedException {
        // 오래 걸리는 일
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(100);

            // UI 갱신
            mTextView.setText(i + "%");
            mProgressBar.setProgress(i);
        }
    }
}
