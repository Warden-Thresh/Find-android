package com.warden.find.contract;


import com.warden.find.model.WeatherWrapper;

/**
 * Created by WangChenchen on 2016/8/30.
 */
public interface WeatherContract {

    interface View {
        void refreshUI(WeatherWrapper weatherWrapper);
    }

    interface Presenter {
        void loadData();
    }
}
