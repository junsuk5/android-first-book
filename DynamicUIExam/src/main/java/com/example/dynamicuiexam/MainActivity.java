package com.example.dynamicuiexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // layout-large 의 레이아웃에는 fragment_container 가 없음
        if (findViewById(R.id.fragment_container) != null) {
            // 화면 회전시에 HeadlinesFragment가 재생성 되는 것을 방지
            if (savedInstanceState == null) {
                HeadlinesFragment headlinesFragment = new HeadlinesFragment();

                // headlinesFragment를 R.id.fragment_container 영역에 추가
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, headlinesFragment)
                        .commit();
            }
        }

    }

    // HeadlinesFragment의 기사제목이 선택되었을 경우에 호출됨
    @Override
    public void onHeadlineSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        // layout-large 의 경우 null이 아님
        if (articleFragment == null) {

            // ArticleFragment프래그먼트를 생성
            ArticleFragment newArticleFragment = new ArticleFragment();
            // Argument로 기사 번호를 전달
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newArticleFragment.setArguments(args);

            // R.id.fragment_container 아이디를 가진 영역의
            // 프래그먼트를 articleFragment로 교체하고
            // 프래그먼트 매니저의 BackStack에 쌓는다
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newArticleFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            articleFragment.updateArticleView(position);
        }

    }

}
