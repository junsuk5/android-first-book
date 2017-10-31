package com.example.packagemanagerexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1000;
    private ImageView mShortcut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShortcut = (ImageView) findViewById(R.id.shortcut_image);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // 저장된 shortcut 값을 얻음. 만약 저장된 값이 없을 경우 기본값으로 null 반환
        String packageName = preferences.getString("shortcut", null);

        if (packageName != null) {
            try {
                Drawable icon = getPackageManager().getApplicationIcon(packageName);
                // 아이콘을 이미지뷰에 표시
                mShortcut.setImageDrawable(icon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // AppListActivity.java로부터 넘겨받은 ApplicationInfo 객체
            // Pacelable 객체를 받기 위해 getParcelableExtra()로 얻음
            ApplicationInfo info = data.getParcelableExtra("info");

            // loadIcon()에 PackageManager를 넘겨주면 아이콘을 Drawable로 얻을 수 있음
            Drawable icon = info.loadIcon(getPackageManager());

            // 기본 SharedPreferences 환경을 얻음
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(this);
            // SharedPrefences를 수정하기 위한 객체를 얻음
            SharedPreferences.Editor edit = preferences.edit();
            // info 객체로 packageName을 얻고, shortcut 키와 함께 프리퍼런스에 저장
            edit.putString("shortcut", info.packageName);
            // 변경사항 적용
            edit.apply();

            mShortcut.setImageDrawable(icon);
        }
    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent(this, AppListActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    public void onImageClicked(View view) {
        // 클릭된 이미지뷰에서 Drawable 객체 얻음
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();

        if (drawable != null) {
            // 프리퍼런스에 shortcut 키로 저장된 패키지명을 가져옴
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(this);
            String packageName = preferences.getString("shortcut", null);

            if (packageName != null) {
                // 이 패키지를 실행할 수 있는 인텐트를 얻어서 액티비티 시작
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
            }
        }

    }

    @Override
    public void onBackPressed() {
        ExitDialogFragment fragment = new ExitDialogFragment();
        fragment.show(getSupportFragmentManager(), "exit");
    }



}
