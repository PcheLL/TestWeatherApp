package com.example.testnumbuster.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnumbuster.Model.WeatherDataManager;
import com.example.testnumbuster.Model.WeatherMain;
import com.example.testnumbuster.Model.WeatherStatus;
import com.example.testnumbuster.Presenter.Presenter;
import com.example.testnumbuster.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class MainView extends AppCompatActivity implements android.view.View.OnClickListener {
    private Presenter presenter;
    MaterialButton materialButtonLeft;
    MaterialButton materialButtonCentral;
    MaterialButton materialButtonRight;
    TextView textViewCityName;
    TextView textViewMainTemperature;
    TextView textViewMinTemperature;
    TextView textViewMaxTemperature;
    TextView textViewHumidity;
    TextView textViewAtmosphericPressure;
    TextView textViewSelectCity;
    MaterialCardView materialCardView;
    ProgressBar progressBar;
    TextView progressStatus;
    ImageView imageViewWeatherStatus;

    /**
     * Начальная установка параметров при инициализации Activity
     *
     * @param savedInstanceState сохранение состояния Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        materialButtonLeft = findViewById(R.id.material_left_button);
        materialButtonLeft.setOnClickListener(this);
        materialButtonCentral = findViewById(R.id.material_central_button);
        materialButtonCentral.setOnClickListener(this);
        materialButtonRight = findViewById(R.id.material_right_button);
        materialButtonRight.setOnClickListener(this);
        textViewCityName = findViewById(R.id.text_view_city_name);
        textViewMainTemperature = findViewById(R.id.text_view_main_temperature);
        textViewMinTemperature = findViewById(R.id.text_view_min_temperature);
        textViewMaxTemperature = findViewById(R.id.text_view_max_temperature);
        textViewHumidity = findViewById(R.id.text_view_humidity);
        textViewAtmosphericPressure = findViewById(R.id.text_view_atmospheric_pressure);
        imageViewWeatherStatus = findViewById(R.id.image_view_weather_status);
        textViewSelectCity = findViewById(R.id.text_view_select_city);
        materialCardView = findViewById(R.id.material_card_view);
        progressBar = findViewById(R.id.progress_bar);
        progressStatus = findViewById(R.id.progress_status);
        textViewSelectCity.setVisibility(View.VISIBLE);
        materialCardView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressStatus.setVisibility(View.GONE);

        WeatherDataManager weatherDataManager = new WeatherDataManager();
        presenter = new Presenter(weatherDataManager, this);
    }

    /**
     * Обработка нажатия на MaterialButton
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.material_left_button:
                presenter.getWeather(1);
                break;

            case R.id.material_central_button:
                presenter.getWeather(2);
                break;

            case R.id.material_right_button:
                presenter.getWeather(3);
                break;
        }

    }

    /**
     * Обновление элементов MaterialCard
     *
     * @param cityId        id выбранного города
     * @param weatherMain   объект класса WeatherMain
     * @param weatherStatus объект класса WeatherStatus
     */
    public void showWeatherData(int cityId, WeatherMain weatherMain, WeatherStatus weatherStatus) {
        String cityName = null;
        if (cityId == 1) {
            cityName = "Москва";
        } else if (cityId == 2) {
            cityName = "Минск";
        } else {
            cityName = "Киев";
        }
        textViewCityName.setText(cityName);
        textViewMainTemperature.setText(String.valueOf(Math.round(weatherMain.getTemp())));
        textViewMinTemperature.setText(String.valueOf(Math.round(weatherMain.getTempMin())));
        textViewMaxTemperature.setText(String.valueOf(Math.round(weatherMain.getTempMax())));
        textViewHumidity.setText(String.valueOf(weatherMain.getHumidity()));
        textViewAtmosphericPressure.setText(String.valueOf(weatherMain.getPressure()));
        String imageStatus = weatherStatus.getStatus();
        switch (imageStatus) {
            case "Thunderstorm":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_thunderstorm_weather));
                break;
            case "Drizzle":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shower_rain_weather));
                break;
            case "Rain":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_rain_weather));
                break;
            case "Snow":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_snow_weather));
                break;
            case "Clear":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_clear_weather));
                break;
            case "Clouds":
                imageViewWeatherStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_weather));
                break;
        }

    }

    /**
     * Отображение ошибки
     */
    public void showFaildMessage() {
        Toast.makeText(this, "Ошибка соединения !!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Cкрываем ProgressBar
     */
    public void hideLoading() {
        textViewSelectCity.setVisibility(View.GONE);
        materialCardView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        progressStatus.setVisibility(View.GONE);

    }

    /**
     * Отображение ProgressBar
     */
    public void showProgressBar() {
        textViewSelectCity.setVisibility(View.GONE);
        materialCardView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        progressStatus.setVisibility(View.VISIBLE);
    }
}
