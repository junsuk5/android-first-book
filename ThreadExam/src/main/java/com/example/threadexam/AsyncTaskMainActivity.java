package com.example.threadexam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Thread, Handler를 AsyncTask로 변경
 */
public class AsyncTaskMainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }

    public void download(View view) {
        new DownLoadTask().execute();
    }

    class DownLoadTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int percent = i;

                // UI 갱신 요청
                publishProgress(percent);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // UI 갱신 요청
            mTextView.setText(values[0] + "%");
            mProgressBar.setProgress(values[0]);
        }
    }
}
