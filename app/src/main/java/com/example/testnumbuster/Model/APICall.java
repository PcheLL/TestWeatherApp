package com.example.testnumbuster.Model;

import com.example.testnumbuster.Model.ForecastWeatherMain;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICall {
    @GET("weather")
    Observable<ForecastWeatherMain> getCurrentWeather(@Query("lat") double latitude,
                                                @Query("lon") double longitude,
                                                @Query("appid") String apiKey,
                                                @Query("units") String units);
}
