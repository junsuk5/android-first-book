package com.example.dynamicuiexam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 제목을 표시할 리스트 프래그먼트
 */
public class HeadlinesFragment extends ListFragment {

    // 이 프래그먼트를 포함하는 액티비티는 반드시 이 인터페이스를 구현해야 함
    interface OnHeadlineSelectedListener {
        // 제목이 선택되었을 때 호출 됨
        void onHeadlineSelected(int position);
    }

    private OnHeadlineSelectedListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Articles 의 Headlines 배열을 사용하여 리스트 뷰를 위한 ArrayAdapter 를 생성
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Articles.Headlines));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 이 프래그먼트를 포함하는 Context는
        // 반드시 OhHeadlineSelectedListener를 구현해야 한다
        // 그렇지 않으면 ClassCastException이 발생하고 앱을 종료한다
        try {
            mListener = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // 선택된 위치를 액티비티에 알려 줌
        if (mListener != null) {
            mListener.onHeadlineSelected(position);
        }
    }
}
