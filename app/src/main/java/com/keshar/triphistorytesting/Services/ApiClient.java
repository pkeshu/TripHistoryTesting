package com.keshar.triphistorytesting.Services;

import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ApiClient implements ApiInterface {
    private ApiInterface apiInterface;
    private final String BASE_URL = "https://krishna.sarathi.cab/";

    public ApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.apiInterface = (ApiInterface) retrofit.create(TripHistoryResponseModel.class);
    }


    @Override
    public Observable<TripHistoryResponseModel> insertTripHistory(Long userId, String token) {
        return this.apiInterface.insertTripHistory(userId, token);
    }
}
