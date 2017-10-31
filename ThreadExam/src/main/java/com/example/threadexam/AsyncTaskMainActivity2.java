package com.example.threadexam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Cancel 처리
 */
public class AsyncTaskMainActivity2 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;
    private DownLoadTask mDownLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDownLoadTask != null && !mDownLoadTask.isCancelled()) {
                    mDownLoadTask.cancel(true);
                }
            }
        });
    }

    public void download(View view) {
        mDownLoadTask = new DownLoadTask();
        mDownLoadTask.execute();
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

                // 취소 됨
                if (isCancelled()) {
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // UI 갱신 요청
            mTextView.setText(values[0] + "%");
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            Toast.makeText(AsyncTaskMainActivity2.this, "취소 됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AsyncTaskMainActivity2.this, "완료 됨", Toast.LENGTH_SHORT).show();
        }
    }
}
