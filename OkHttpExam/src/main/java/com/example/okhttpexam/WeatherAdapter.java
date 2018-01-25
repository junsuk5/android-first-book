package com.example.okhttpexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.httpnetworkexam.R;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    private final List<Weather> mList;

    public WeatherAdapter(List<Weather> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);

            holder = new ViewHolder();
            holder.country = (TextView) convertView.findViewById(R.id.country_text);
            holder.weather = (TextView) convertView.findViewById(R.id.weather_text);
            holder.temperature = (TextView) convertView.findViewById(R.id.temperature_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather weather = (Weather) getItem(position);
        holder.country.setText(weather.getCountry());
        holder.weather.setText(weather.getWeather());
        holder.temperature.setText(weather.getTemperature());

        return convertView;
    }

    // 뷰 홀더 패턴
    static class ViewHolder {
        TextView country;
        TextView weather;
        TextView temperature;
    }
}
