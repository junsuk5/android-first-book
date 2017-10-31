package com.example.styleexam;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by junsuk on 2017. 8. 3..
 */

public class NewToast extends Toast {

    public NewToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String message, int duration) {
        Toast toast = new Toast(context);

        // Toast의 레이아웃
        View customView = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);

        // Toast에 표시될 TextView
        TextView textView = (TextView) customView.findViewById(R.id.message);
        textView.setText(message);

        // Toast에 레이아웃 설정
        toast.setView(customView);
        toast.setDuration(Toast.LENGTH_SHORT);

        // 위치 조정
        toast.setGravity(Gravity.CENTER, 0, -300);

        return toast;
    }
}
