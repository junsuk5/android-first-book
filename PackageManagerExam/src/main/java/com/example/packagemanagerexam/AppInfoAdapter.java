package com.example.packagemanagerexam;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by junsuk on 2017. 6. 23..
 */

public class AppInfoAdapter extends BaseAdapter {
    private List<ApplicationInfo> mInfos;

    public AppInfoAdapter(List<ApplicationInfo> data) {
        mInfos = data;
    }


    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_app, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon_image);
            holder.textView = (TextView) convertView.findViewById(R.id.app_name_text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 앱 정보
        ApplicationInfo info = mInfos.get(position);

        // 앱 아이콘
        Drawable icon = info.loadIcon(parent.getContext().getPackageManager());
        holder.imageView.setImageDrawable(icon);

        // 앱 이름
        String name =
                String.valueOf(info.loadLabel(parent.getContext().getPackageManager()));
        holder.textView.setText(name);

        return convertView;
    }

    // 뷰 홀더 패턴을 위한 홀더 클래스
    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
