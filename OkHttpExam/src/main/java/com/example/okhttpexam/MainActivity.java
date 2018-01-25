package com.example.okhttpexam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.httpnetworkexam.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView mWeatherListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherListView = (ListView) findViewById(R.id.list_view);

        // 소스를 확인하고 싶은 사이트 주소
        new HttpAsyncTask().execute("https://goo.gl/eIXu9l");
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, List<Weather>> {
        private final String TAG = HttpAsyncTask.class.getSimpleName();

        // OkHttp 클라이언트
        OkHttpClient client = new OkHttpClient();

        @Override
        protected List<Weather> doInBackground(String... params) {
            List<Weather> weatherList = new ArrayList<>();
            String strUrl = params[0];
            try {
                // 요청
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();
                // 응답
                Response response = client.newCall(request).execute();

                Gson gson = new Gson();

                // import java.lang.reflect.Type
                Type listType = new TypeToken<ArrayList<Weather>>() {
                }.getType();
                weatherList = gson.fromJson(response.body().string(), listType);

                Log.d(TAG, "onCreate: " + weatherList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return weatherList;
        }

        @Override
        protected void onPostExecute(List<Weather> weatherList) {
            super.onPostExecute(weatherList);

            if (weatherList != null) {
                Log.d("HttpAsyncTask", weatherList.toString());
                WeatherAdapter adapter = new WeatherAdapter(weatherList);
                mWeatherListView.setAdapter(adapter);
            }
        }
    }

}
