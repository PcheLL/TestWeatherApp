package com.example.testnumbuster.Model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ForecastWeatherMain {
    @SerializedName("weather")
    private List<WeatherStatus> mWeatherStatus = new ArrayList<>();
    @SerializedName("main")
    private WeatherMain mWeatherMain;
}
