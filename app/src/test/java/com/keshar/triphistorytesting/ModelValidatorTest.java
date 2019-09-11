package com.keshar.triphistorytesting;

import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Model.Trips;
import com.keshar.triphistorytesting.ModelBuilder.TripHistoryModelBuilder;
import com.keshar.triphistorytesting.ModelBuilder.TripModelBuilder;
import com.keshar.triphistorytesting.Validator.ModelValidator;

import org.junit.Test;

public class ModelValidatorTest {
    @Test
    public void shouldNotThrowErrorOnValidTripsModel() throws IllegalArgumentException {
        TripModelBuilder builder = new TripModelBuilder();
        builder.setId(1l);
        builder.setAcceptedTime(20550829l);
        builder.setDestinationAddress("New Baneshwor, Kathmandu, Nepal");
        builder.setSourceAddress("Kantipath, Kathmandu, Nepal");
        builder.setIsTripCancel(0);
        builder.setIsTripCancel(1);
        builder.setPaymentMode(2);
        builder.setProviderId("123123123");
        builder.setTotal(340l);
        Trips trips = builder.build();

        ModelValidator validator = new ModelValidator(trips);
        validator.validate();
    }

    @Test
    public void shouldNotThrowErrorOnValidTripHistoryModel() throws IllegalArgumentException {
        TripHistoryModelBuilder tripHistoryModelBuilder = new TripHistoryModelBuilder();
        tripHistoryModelBuilder.setSuccess(true);
        TripHistoryResponseModel tripHistoryResponseModel = tripHistoryModelBuilder.build();
        ModelValidator validator = new ModelValidator(tripHistoryResponseModel);
        validator.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnInvalidTripsModel() throws IllegalArgumentException {
        TripModelBuilder builder = new TripModelBuilder();
        builder.setId(null);
        builder.setAcceptedTime(null);
        builder.setDestinationAddress(null);
        builder.setSourceAddress(null);
        builder.setIsTripCancel(0);
        builder.setIsTripCancel(0);
        builder.setPaymentMode(0);
        builder.setProviderId(null);
        builder.setTotal(null);
        Trips trips = builder.build();

        ModelValidator validator = new ModelValidator(trips);
        validator.validate();
    }

    @Test
    public void shouldThrowErrorOnInvalidTripHistoryModel() throws IllegalArgumentException {
        TripHistoryModelBuilder tripHistoryModelBuilder = new TripHistoryModelBuilder();
        tripHistoryModelBuilder.setSuccess(false);
        TripHistoryResponseModel tripHistoryResponseModel = tripHistoryModelBuilder.build();
        ModelValidator validator = new ModelValidator(tripHistoryResponseModel);
        validator.validate();
    }
}
