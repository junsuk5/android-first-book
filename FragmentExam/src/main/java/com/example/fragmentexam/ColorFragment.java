package com.example.fragmentexam;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {
    private int mColor = Color.BLUE;
    private TextView mHelloTextView;

    public ColorFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        mHelloTextView = (TextView) view.findViewById(R.id.hello_text);
        mHelloTextView.setBackgroundColor(mColor);
        return view;
    }

    public void setColor(int color) {
        // 텍스트 뷰의 배경색을 변경
        mColor = color;
        if (mHelloTextView != null) {
            mHelloTextView.setBackgroundColor(mColor);
        }

    }


}
