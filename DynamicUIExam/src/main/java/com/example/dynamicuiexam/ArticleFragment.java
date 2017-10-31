package com.example.dynamicuiexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * HeadlinesFragment를 클릭했을 때 기사를 표시할 프래그먼트
 */
public class ArticleFragment extends Fragment {
    public static final String ARG_POSITION = "position";
    private int mCurrentPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 화면이 회전되면 이전에 선택된 위치를 복원
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        // 화면 레이아웃은 TextView 하나만 있는 레이아웃을 사용
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            // 프래그먼트가 생성되었을 경우
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // 화면 회전 등의 경우
            updateArticleView(mCurrentPosition);
        }
    }

    // 선택된 기사를 표시
    public void updateArticleView(int position) {
        TextView article = (TextView) getView().findViewById(R.id.article_text);
        article.setText(Articles.Articles[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // 화면이 회전될 때, 선택된 위치를 저장
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
