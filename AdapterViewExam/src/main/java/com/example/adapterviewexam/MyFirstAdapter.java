package com.example.adapterviewexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junsuk on 2017. 6. 22..
 */

public class MyFirstAdapter extends BaseAdapter {
    private final List<Weather> mData;

    private Map<String, Integer> mWeatherImagaMap;


    // List 를 구현한 모든 것(ArrayList 등) 을 받는 생성자
    public MyFirstAdapter(List<Weather> data) {
        mData = data;

        mWeatherImagaMap = new HashMap<>();
        mWeatherImagaMap.put("맑음", R.drawable.sunny);
        mWeatherImagaMap.put("폭설", R.drawable.blizzard);
        mWeatherImagaMap.put("구름", R.drawable.cloudy);
        mWeatherImagaMap.put("비", R.drawable.rainy);
        mWeatherImagaMap.put("눈", R.drawable.snow);
    }

    // 아이템의 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position 번째의 아이템
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position 번째의 아이디
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position 번째의 아이템의 View 를 구성하는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather, parent, false);

            // 날씨, 도시, 기온 View
            ImageView weatherImage = (ImageView) convertView.findViewById(R.id.weather_image);
            TextView cityText = (TextView) convertView.findViewById(R.id.city_text);
            TextView tempText = (TextView) convertView.findViewById(R.id.city_text);

            holder.weatherImage = weatherImage;
            holder.cityText = cityText;
            holder.tempText = tempText;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // 현재 position 의 날씨 데이터
        Weather weather = mData.get(position);

        // 데이터 설정 => 홀더에 저장
        holder.cityText.setText(weather.getCity());
        holder.tempText.setText(weather.getTemp());
        holder.weatherImage.setImageResource(mWeatherImagaMap.get(weather.getWeather()));

        return convertView;
    }

    static class ViewHolder {
        ImageView weatherImage;
        TextView cityText;
        TextView tempText;
    }


}
