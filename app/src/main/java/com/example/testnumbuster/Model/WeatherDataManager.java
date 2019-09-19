package com.example.testnumbuster.Model;

import com.example.testnumbuster.Model.Const.Constant;

import io.reactivex.Observable;

public class WeatherDataManager {

    /**
     * Получение данных о погоде
     *
     * @param cityId id города
     */
    public Observable<ForecastWeatherMain> getWeatherData(int cityId)
    {
        DatabaseWeatherManager databaseWeatherManager = new DatabaseWeatherManager();
        double lat = databaseWeatherManager.getLatLocation(cityId);
        double lon = databaseWeatherManager.getLngLocation(cityId);
        APIWeather apiWeather = new APIWeather();
        return apiWeather.getCurrentWeather().getCurrentWeather(lat,lon, Constant.API_KEY,Constant.WEATHER_UNITS);
    }
}
