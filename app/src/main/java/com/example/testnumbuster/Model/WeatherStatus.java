package com.example.testnumbuster.Model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class WeatherStatus {
    @SerializedName("main")
    private String status;
}
