package com.example.threadexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 오래 걸리는 처리를 Thread로 감싸지만 UI변경 코드도 함께 감쌌기 때문에 잘못된 예
 */
public class MainActivity2 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);

        String s = "[\n" +
                "  {\n" +
                "    \"country\": \"한국\",\n" +
                "    \"weather\": \"비\",\n" +
                "    \"temperature\": \"20\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"country\": \"일본\",\n" +
                "    \"weather\": \"맑음\",\n" +
                "    \"temperature\": \"19\"\n" +
                "  }\n" +
                "]\n";
        try {
            JSONArray jsonArray = new JSONArray("[\n" +
                    "  {\n" +
                    "    \"country\": \"한국\",\n" +
                    "    \"weather\": \"비\",\n" +
                    "    \"temperature\": \"20\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"country\": \"일본\",\n" +
                    "    \"weather\": \"맑음\",\n" +
                    "    \"temperature\": \"19\"\n" +
                    "  }\n" +
                    "]\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

                    // UI 갱신
                    mTextView.setText(i + "%");
                    mProgressBar.setProgress(i);
                }
            }
        }).start();
    }
}
