package com.example.packagemanagerexam;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class AppListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        ListView listView = (ListView) findViewById(R.id.list_view);

        // 기기에 설치된 모든 앱 목록
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> infos =
                pm.getInstalledApplications(PackageManager.GET_META_DATA);

        AppInfoAdapter adapter = new AppInfoAdapter(infos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo info = (ApplicationInfo)
                        (parent.getAdapter()).getItem(position);

                Intent intent = new Intent();
                intent.putExtra("info", info);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
