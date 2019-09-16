package com.keshar.triphistorytesting.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keshar.triphistorytesting.Util.IsDefine;

import java.util.List;

public class TripHistoryResponseModel {
    @IsDefine
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("trip")
    @Expose
    private List<Trips> trips;

    public TripHistoryResponseModel() {
    }

    public TripHistoryResponseModel(boolean success, List<Trips> trips) {
        this.success = success;
        this.trips = trips;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Trips> getTrips() {
        return trips;
    }
}
