package com.example.adapterviewexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 자료
//        ArrayList<String> data = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            data.add("data " + i);
//        }

        ArrayList<Weather> data = new ArrayList<>();
        data.add(new Weather("수원", "25도", "맑음"));
        data.add(new Weather("서울", "26도", "비"));
        data.add(new Weather("안양", "24도", "구름"));
        data.add(new Weather("부산", "29도", "구름"));
        data.add(new Weather("인천", "23도", "맑음"));
        data.add(new Weather("대구", "28도", "비"));
        data.add(new Weather("용인", "25도", "비"));


        // 어댑터
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, data);

        MyFirstAdapter adapter = new MyFirstAdapter(data);

        // 뷰
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


    }
}
