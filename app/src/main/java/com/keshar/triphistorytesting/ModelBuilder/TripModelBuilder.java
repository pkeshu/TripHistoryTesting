package com.keshar.triphistorytesting.ModelBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keshar.triphistorytesting.Model.Trips;

public class TripModelBuilder {
    private String sourceAddress;
    private String destinationAddress;
    private Long total;
    private String providerId;
    private int paymentMode;
    private int isTripCompleted;
    private int isTripCancel;
    private String acceptedTime;
    private String id;

    public TripModelBuilder setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
        return this;
    }

    public TripModelBuilder setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public TripModelBuilder setTotal(Long total) {
        this.total = total;
        return this;
    }

    public TripModelBuilder setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public TripModelBuilder setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public TripModelBuilder setIsTripCompleted(int isTripCompleted) {
        this.isTripCompleted = isTripCompleted;
        return this;
    }

    public TripModelBuilder setIsTripCancel(int isTripCancel) {
        this.isTripCancel = isTripCancel;
        return this;
    }

    public TripModelBuilder setAcceptedTime(String acceptedTime) {
        this.acceptedTime = acceptedTime;
        return this;
    }

    public TripModelBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public Trips build() {
        return new Trips(this.sourceAddress,
                this.destinationAddress,
                this.total,
                this.providerId,
                this.paymentMode,
                this.isTripCompleted,
                this.isTripCancel,
                this.acceptedTime,
                this.id
        );
    }
}
