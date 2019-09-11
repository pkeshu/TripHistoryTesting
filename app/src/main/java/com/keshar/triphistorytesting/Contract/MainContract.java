package com.keshar.triphistorytesting.Contract;

import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;

public interface MainContract {

    interface View {

        void onFetchDataStarted();

        void onFetchDataCompleted();

        void onFetchDataSuccess(TripHistoryResponseModel tripHistoryResponseModel);

        void onFetchDataError(Throwable e);
    }

    interface Presenter {

        void loadData();

        void subscribe();

        void unsubscribe();

        void onDestroy();

    }
}
