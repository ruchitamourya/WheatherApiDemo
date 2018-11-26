package com.example.ruchi.wheatherapidemo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("/data/2.5/weather")
    Call<JsonObject> getWeather(@Query("q") String city, @Query("APPID") String appId);

}
