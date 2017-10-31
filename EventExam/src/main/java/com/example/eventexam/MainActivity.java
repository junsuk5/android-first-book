package com.example.eventexam;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mView1;
    private View mView2;
    private EditText mEdit1;
    private EditText mEdit2;
    private TextView mEventInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView1 = (TextView) findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);

        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);

        mEventInfoTextView = (TextView) findViewById(R.id.event_info_text);

        // 클릭
        setClickEvent();
        // 포커스 변경
        setFocusEvent();
        // 키
        setKeyEvent();
        // 터치
        setTouchEvent();
    }

    private void setTouchEvent() {
        mView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "터치 다운", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEventInfoTextView.setText("터치 정보 : " + event.toString());
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this, "터치 업", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void setKeyEvent() {
        View.OnKeyListener keyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 엔터키를 뗄 때 토스트를 표시
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Toast.makeText(MainActivity.this, "뒤로가기를 눌렀습니다", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
        View.OnKeyListener keyListener2 = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 엔터키를 뗄 때 토스트를 표시
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Toast.makeText(MainActivity.this, "뒤로가기를 눌렀습니다", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        };
        mEdit1.setOnKeyListener(keyListener);
        mEdit2.setOnKeyListener(keyListener2);
    }

    private void setFocusEvent() {
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 포커스를 가지면 배경색을 빨간색으로
                if (hasFocus) {
                    v.setBackgroundColor(Color.RED);
                } else {
                    v.setBackgroundColor(Color.WHITE);
                }
            }
        };

        mEdit1.setOnFocusChangeListener(focusChangeListener);
        mEdit2.setOnFocusChangeListener(focusChangeListener);
    }

    private void setClickEvent() {
        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "클릭", Toast.LENGTH_SHORT).show();
            }
        });
        // 롱 클릭
        mView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "롱 클릭", Toast.LENGTH_SHORT).show();
                // 이벤트 소비
                return true;
            }
        });
    }

}
