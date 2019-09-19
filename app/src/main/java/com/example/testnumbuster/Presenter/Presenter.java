package com.example.testnumbuster.Presenter;

import android.util.Log;

import com.example.testnumbuster.Model.ForecastWeatherMain;
import com.example.testnumbuster.Model.WeatherDataManager;
import com.example.testnumbuster.Model.WeatherMain;
import com.example.testnumbuster.Model.WeatherStatus;
import com.example.testnumbuster.View.MainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements InterfacePresenter {
    private Observable<ForecastWeatherMain> responseObservable;
    private WeatherDataManager weatherDataManager;
    private MainView mainView;
    private WeatherMain weatherMain;
    private WeatherStatus weatherStatus;

    /**
     * Конструктор Presenter
     *
     * @param weatherDataManager объект класса WeatherDataManager
     * @param mainView           объект класса MainView
     */
    public Presenter(WeatherDataManager weatherDataManager, MainView mainView) {
        this.weatherDataManager = weatherDataManager;
        this.mainView = mainView;
    }

    /**
     * Получение данных и погоде и отпрвка обновлений в MainView
     *
     * @param cityId id выбранного города
     */
    @Override
    public void getWeather(final int cityId) {
        showProgressBar();
        responseObservable = weatherDataManager.getWeatherData(cityId);

        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ForecastWeatherMain>() {
                               @Override
                               public void onNext(ForecastWeatherMain forecastWeatherMain) {
                                   weatherMain = forecastWeatherMain.getMWeatherMain();
                                   weatherStatus = forecastWeatherMain.getMWeatherStatus().get(0);
                                   mainView.showWeatherData(cityId, weatherMain, weatherStatus);
                                   Log.d("TAG", "onNext");
                               }

                               @Override
                               public void onError(Throwable t) {
                                   showFaildMessage();
                               }

                               @Override
                               public void onComplete() {
                                   hideLoading();
                               }
                           }
                );
    }

    /**
     * Отправка отображения ошибки в MainView
     */
    @Override
    public void showFaildMessage() {
        mainView.showFaildMessage();
    }

    /**
     * Отправка отображения загрузки в MainView
     */
    @Override
    public void showProgressBar() {
        mainView.showProgressBar();
    }

    /**
     * Отпрка скрытия отображения загрузки в MainView
     */
    @Override
    public void hideLoading() {
        mainView.hideLoading();
    }
}
