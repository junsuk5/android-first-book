package com.suwonsmartapp.quickdustinfo.finedust;

import com.suwonsmartapp.quickdustinfo.model.FineDust;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public interface FineDustContract {
    interface View {
        void showFineDustResult(FineDust fineDust);

        void showLoadError(String message);

        void loadingStart();

        void loadingEnd();

        void reload(double lat, double lng);
    }

    interface UserActionsListener {
        void loadFineDustData();
    }
}
