package com.keshar.triphistorytesting.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keshar.triphistorytesting.Model.TripHistoryResponseModel;
import com.keshar.triphistorytesting.Model.Trips;
import com.keshar.triphistorytesting.Util.IsDefine;

import java.util.ArrayList;
import java.util.List;

public class TripHistoryParser {
    private static Gson gson = new Gson();

    public TripHistoryResponseModel parseTripHistoryFromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new TripHistoryResponseModel();
        }
        boolean success = false;
        List<Trips> trips = new ArrayList<>();
        String sourceAddress = null;
        String destinationAddress = null;
        Long total = null;
        String providerId = null;
        int paymentMode = 0;
        int isTripCompleted = 0;
        int isTripCancel = 0;
        String acceptedTime = null;
        String id = null;
        try {
            JsonObject input = gson.fromJson(json, JsonObject.class);
            if (input.has("success")) {
                success = input.get("success").getAsBoolean();
            }
            if (input.has("trips") && input.size() > 0) {
                JsonArray tripList = input.getAsJsonArray("trips");
                if (tripList != null && tripList.size() > 0) {
                    JsonObject firstTrip = tripList.get(0).getAsJsonObject();
                    if (firstTrip != null) {
                        if (firstTrip.has("source_address")) {
                            sourceAddress = firstTrip.get("source_address").getAsString();
                        }
                        if (firstTrip.has("destination_address")) {
                            destinationAddress = firstTrip.get("destination_address").getAsString();
                        }
                        if (firstTrip.has("total")) {
                            total = firstTrip.get("total").getAsLong();
                        }
                        if (firstTrip.has("provider_id")) {
                            providerId = firstTrip.get("provider_id").getAsString();
                        }
                        if (firstTrip.has("payment_mode")) {
                            paymentMode = firstTrip.get("payment_mode").getAsInt();
                        }
                        if (firstTrip.has("is_trip_completed")) {
                            isTripCompleted = firstTrip.get("is_trip_completed").getAsInt();
                        }
                        if (firstTrip.has("is_trip_cancelled")) {
                            isTripCancel = firstTrip.get("is_trip_cancelled").getAsInt();
                        }
                        if (firstTrip.has("accepted_time") && !firstTrip.get("accepted_time").isJsonNull()) {
                            acceptedTime = firstTrip.get("accepted_time").toString();
                        }
                        if (firstTrip.has("_id") && !firstTrip.get("_id").isJsonNull()) {
                            id = firstTrip.get("_id").toString();
                        }

                    }
                }
                trips.add(new Trips(sourceAddress,
                        destinationAddress,
                        total,
                        providerId,
                        paymentMode,
                        isTripCompleted,
                        isTripCancel,
                        acceptedTime,
                        id));
                return new TripHistoryResponseModel(success, trips);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return new TripHistoryResponseModel();
    }

}
