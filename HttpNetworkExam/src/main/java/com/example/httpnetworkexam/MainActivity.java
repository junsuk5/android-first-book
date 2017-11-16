package com.example.httpnetworkexam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 소스를 확인하고 싶은 사이트 주소
        new HttpAsyncTask().execute("https://goo.gl/eIXu9l");
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;

            String strUrl = params[0];

            try {
                // URL 객체 생성
                URL url = new URL(strUrl);
                // URL을 연결한 객체 생성
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");     // GET 방식 통신
                connection.setDoOutput(true);           // 쓰기 모드
                connection.setDoInput(true);            // 읽기 모드
                connection.setUseCaches(false);         // 캐시 사용
                connection.setDefaultUseCaches(false);

                // 입력 스트림 열기
                InputStream inputStream = connection.getInputStream();

                // 문자열 저장 객체
                StringBuilder builder = new StringBuilder();
                // UTF-8 방식으로 입력받은 스트림을 읽어들이는 버퍼 객체
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"));

                // 한 줄씩 문자열을 읽어들이기
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }

                // 결과
                result = builder.toString();

            } catch (MalformedURLException e) {
                // 에러 처리
                e.printStackTrace();
            } catch (IOException e) {
                // 에러 처리
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {
                Log.d("HttpAsyncTask", s);
            }
        }
    }


}
