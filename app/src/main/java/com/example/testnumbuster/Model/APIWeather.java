package com.example.testnumbuster.Model;

import com.example.testnumbuster.Model.Database.DatabaseHelper;
import com.example.testnumbuster.Model.Const.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIWeather {
    private APICall apiCall;
    private Retrofit retrofit;
    private DatabaseHelper databaseHelper;

    /**
     * Конструктор класса APIWeather. Задаем URL, создаем конвертер, добавляем адаптер
     */
    public APIWeather() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.WEATHER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiCall = retrofit.create(APICall.class);
    }

    /**
     * Обращаемся к ApiCall для получения состояния погоды
     */
    public APICall getCurrentWeather() {
        return this.apiCall;
    }
}
