package com.example.callbackexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ColorListFragment.OnColorSelectedListener {

    private ColorFragment mColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorFragment = (ColorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_color);
//        colorFragment.setColor(Color.RED);
    }

    @Override
    public void onColorSelected(int color) {
        mColorFragment.setColor(color);
    }

}
