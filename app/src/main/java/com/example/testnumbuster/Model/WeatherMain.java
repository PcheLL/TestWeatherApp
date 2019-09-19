package com.example.testnumbuster.Model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class WeatherMain {
    @SerializedName("temp")
    private float temp;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private double humidity;

    @SerializedName("temp_min")
    private float tempMin;

    @SerializedName("temp_max")
    private float tempMax;
}
