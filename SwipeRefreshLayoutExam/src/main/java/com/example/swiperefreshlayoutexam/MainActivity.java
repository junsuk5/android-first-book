package com.example.swiperefreshlayoutexam;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        // 색 설정
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN);

        // 리스너 설정
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        // 갱신 처리 시작
        // 예제에서는 단순히 3초 후에 처리가 종료된 것으로 함
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 갱신 처리가 끝나면 인디케이터를 비표시
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "로딩이 완료되었습니다", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }
}
