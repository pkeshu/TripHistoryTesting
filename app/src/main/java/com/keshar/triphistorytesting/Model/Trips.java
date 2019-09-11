package com.keshar.triphistorytesting.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keshar.triphistorytesting.Util.IsDefine;

public class Trips {
    @IsDefine
    @SerializedName("source_address")
    @Expose
    private String sourceAddress;
    @IsDefine
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @IsDefine
    @SerializedName("total")
    @Expose
    private Long total;
    @IsDefine
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @IsDefine
    @SerializedName("payment_mode")
    @Expose
    private int paymentMode;
    @IsDefine
    @SerializedName("is_trip_completed")
    @Expose
    private int isTripCompleted;
    @IsDefine
    @SerializedName("is_trip_cancelled")
    @Expose
    private int isTripCancel;
    @IsDefine
    @SerializedName("accepted_time")
    @Expose
    private Long acceptedTime;
    @IsDefine
    @SerializedName("_id")
    @Expose
    private Long id;

    public Trips(String sourceAddress, String destinationAddress, Long total, String providerId, int paymentMode, int isTripCompleted, int isTripCancel, Long acceptedTime, Long id) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.total = total;
        this.providerId = providerId;
        this.paymentMode = paymentMode;
        this.isTripCompleted = isTripCompleted;
        this.isTripCancel = isTripCancel;
        this.acceptedTime = acceptedTime;
        this.id = id;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public Long getTotal() {
        return total;
    }

    public String getProviderId() {
        return providerId;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public int getIsTripCompleted() {
        return isTripCompleted;
    }

    public int getIsTripCancel() {
        return isTripCancel;
    }

    public Long getAcceptedTime() {
        return acceptedTime;
    }

    public Long getId() {
        return id;
    }
}
