package com.example.lifecycleexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";

    private TextView mLevelText;
    private TextView mScoreText;

    private int mLevel = 0;
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLevelText = (TextView) findViewById(R.id.level_text);
        mScoreText = (TextView) findViewById(R.id.score_text);

        if (savedInstanceState == null) {
            // 초기화 할 코드
        } else {
            // 상태 복원
            mLevel = savedInstanceState.getInt(STATE_LEVEL);
            mScore = savedInstanceState.getInt(STATE_SCORE);
            mLevelText.setText("레벨 : " + mLevel);
            mScoreText.setText("점수 : " + mScore);
        }

    }

//    상태 복원을 위한 또 다른 방법
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        // EditText 등의 복원을 위해 항상 호출 해야 함
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // 상태 복원
//        mLevel = savedInstanceState.getInt(STATE_LEVEL);
//        mScore = savedInstanceState.getInt(STATE_SCORE);
//        mLevelText.setText("레벨 : " + mLevel);
//        mScoreText.setText("점수 : " + mScore);
//    }


    public void onLevelUp(View view) {
        mLevel++;
        mLevelText.setText("레벨 : " + mLevel);
    }

    public void onScoreUp(View view) {
        mScore += 100;
        mScoreText.setText("점수 : " + mScore);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 상태 저장
        outState.putInt(STATE_SCORE, mScore);
        outState.putInt(STATE_LEVEL, mLevel);

        // 항상 슈퍼클래스의 메서드를 호출해야 합니다
        super.onSaveInstanceState(outState);
    }


}
