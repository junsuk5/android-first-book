package com.example.loginexam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private CheckBox mSaveCheckBox;

    // 설정 정보 저장 객체
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailEditText = (EditText) findViewById(R.id.email_edit);
        mSaveCheckBox = (CheckBox) findViewById(R.id.save_check);

        // Preference 객체 초기화
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // 저장된 이메일을 불러와서 복원
        Boolean isChecked = mPreferences.getBoolean("save", false);
        mSaveCheckBox.setChecked(isChecked);
        if (isChecked) {
            String email = mPreferences.getString("email", "");
            mEmailEditText.setText(email);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // SharedPreferences의 수정 가능한 객체 얻기
        SharedPreferences.Editor editor = mPreferences.edit();
        // 저장할 데이터
        editor.putBoolean("save", mSaveCheckBox.isChecked());
        editor.putString("email", mEmailEditText.getText().toString());
        // 저장
        editor.apply();
    }

}
