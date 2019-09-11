package com.keshar.triphistorytesting.Services;

import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiInterface {
    @POST("userhistory/")
    Observable<TripHistoryResponseModel> insertTripHistory(@Query("user_id") Long userId, @Query("token") String token);
}
