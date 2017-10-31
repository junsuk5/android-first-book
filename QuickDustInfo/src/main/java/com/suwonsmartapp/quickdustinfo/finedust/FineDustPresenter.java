package com.suwonsmartapp.quickdustinfo.finedust;


import com.suwonsmartapp.quickdustinfo.data.FineDustRepository;
import com.suwonsmartapp.quickdustinfo.model.FineDust;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public class FineDustPresenter implements FineDustContract.UserActionsListener {


    private final FineDustRepository mRepository;
    private final FineDustContract.View mView;

    public FineDustPresenter(FineDustRepository repository, FineDustContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void loadFineDustData() {
        // 데이터 제공이 가능하면
        if (mRepository.isAvailable()) {
            // 로딩 시작
            mView.loadingStart();

            // 데이터 가져오기
            mRepository.getFineDustData(new Callback<FineDust>() {
                @Override
                public void onResponse(Call<FineDust> call, Response<FineDust> response) {
                    // 데이터 표시하기
                    mView.showFineDustResult(response.body());
                    // 로딩 끝
                    mView.loadingEnd();
                }

                @Override
                public void onFailure(Call<FineDust> call, Throwable t) {
                    // 에러 표시하기
                    mView.showLoadError(t.getLocalizedMessage());
                    // 로딩 끝
                    mView.loadingEnd();
                }
            });
        }
    }
}
