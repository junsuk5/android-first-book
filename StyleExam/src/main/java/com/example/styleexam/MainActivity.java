package com.example.styleexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNewToast(View view) {
        NewToast.makeText(this, "이것이 뉴 토스트다", Toast.LENGTH_SHORT).show();
    }
}
