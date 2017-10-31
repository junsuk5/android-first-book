package com.example.viewexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "버튼이 눌렸습니다", Toast.LENGTH_SHORT).show();
//            }
//        });
//
        TextView textView = findViewById(R.id.hello_text);
        textView.setText("안녕하세요");
    }

    public void onButtonClicked(View view) {
    }

    public void onVisibleClicked(View view) {
    }

    public void on보입니까Clicked(View view) {
    }
}
