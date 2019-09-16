package com.keshar.triphistorytesting;

import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Model.Trips;
import com.keshar.triphistorytesting.Util.FileReaderUtil;
import com.keshar.triphistorytesting.Parser.*;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParseTripHistoryFromJsonTest {

    public static final String WORD = "some word";
    public static final String SOURCEADDRESS = "Kantipath, Kathmandu, Nepal";
    public static final String DESTINATIONADDRESS = "New Baneshwor, Kathmandu, Nepal";
    public static final String PROVIDERID_ID = "123123123";
    private static String tripHistory;
    TripHistoryParser SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new TripHistoryParser();
        ClassLoader classLoader = this.getClass().getClassLoader();
        tripHistory = FileReaderUtil.readFile(classLoader, "tripHistory.json");
    }

    @Test
    public void parseTripHistoryFromJson_NullInput_EmptyResult() {

        TripHistoryResponseModel tripHistoryResponseModel = SUT.parseTripHistoryFromJson(null);
        assertThatTripHistoryIsEmpty(tripHistoryResponseModel);
    }

    @Test
    public void parseTripHistoryFromJson_EmptyInput_EmptyResult() {
        TripHistoryResponseModel tripHistoryResponseModel = SUT.parseTripHistoryFromJson("");
        assertThatTripHistoryIsEmpty(tripHistoryResponseModel);
    }

    @Test
    public void parseTripHistoryFromJson_EmptyJson_EmptyResult() {
        TripHistoryResponseModel tripHistoryResponseModel = SUT.parseTripHistoryFromJson("{}");
        assertThatTripHistoryIsEmpty(tripHistoryResponseModel);
    }
    @Test
    public void parseTripHistoryFromJson_NotAJsonInput_EmptyResult() {
        TripHistoryResponseModel tripHistoryResponseModel = SUT.parseTripHistoryFromJson(WORD);
        assertThatTripHistoryIsEmpty(tripHistoryResponseModel);
    }

    @Test
    public void parseTripHistoryFromJson_CorrectInput_ReturnTripHistory() {
        TripHistoryResponseModel tripHistoryResponseModel = SUT.parseTripHistoryFromJson(tripHistory);
        assertThat(tripHistoryResponseModel.isSuccess(), is(true));
        asserThatTripIsCorrect(tripHistoryResponseModel.getTrips());

    }

    private void asserThatTripIsCorrect(List<Trips> trips) {
        Trips fristTrip = trips.get(0);
        assertThat(fristTrip.getSourceAddress(), is(SOURCEADDRESS));
        assertThat(fristTrip.getDestinationAddress(), is(DESTINATIONADDRESS));
        assertThat(fristTrip.getAcceptedTime(), is(nullValue()));
        assertThat(fristTrip.getId(), is(nullValue()));
        assertThat(fristTrip.getIsTripCancel(), is(0));
        assertThat(fristTrip.getIsTripCompleted(), is(1));
        assertThat(fristTrip.getTotal(), Is.<Long>is(Long.valueOf(340)));
        assertThat(fristTrip.getProviderId(), is(PROVIDERID_ID));
    }

    private void assertThatTripHistoryIsEmpty(TripHistoryResponseModel tripHistoryResponseModel) {
        assertThat(tripHistoryResponseModel.getTrips(), nullValue());
        assertThat(tripHistoryResponseModel.isSuccess(), is(false));
    }
}
