package com.keshar.triphistorytesting.ModelBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Model.Trips;

import java.util.List;

public class TripHistoryModelBuilder {
    private boolean success;
    private List<Trips> trips;

    public TripHistoryModelBuilder setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public TripHistoryModelBuilder setTrips(List<Trips> trips) {
        this.trips = trips;
        return this;
    }

    public TripHistoryResponseModel build(){
        return new TripHistoryResponseModel(this.success,
                this.trips);
    }
}
