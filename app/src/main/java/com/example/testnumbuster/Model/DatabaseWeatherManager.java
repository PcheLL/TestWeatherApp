package com.example.testnumbuster.Model;

import com.example.testnumbuster.Model.Database.App;
import com.example.testnumbuster.Model.Database.DataDao;
import com.example.testnumbuster.Model.Database.DataModel;
import com.example.testnumbuster.Model.Database.DatabaseHelper;

public class DatabaseWeatherManager {
    private DatabaseHelper databaseHelper;
    private DataDao dataDao;

    /**
     * Конструктор класса DatabaseWeatherManager. Отправка в БД координат
     * сделана для примера записи данных в БЛ.
     */
    public DatabaseWeatherManager() {
        databaseHelper = App.getInstance().getDatabaseInstance();
        dataDao = databaseHelper.getDataDao();
        databaseHelper.clearAllTables(); // Для отчистки старых таблиц SQLite на устройстве

        DataModel model_Moscow = new DataModel();
        model_Moscow.setId(1);
        model_Moscow.setLatitude("55.751244");
        model_Moscow.setLongitude("37.618423");
        dataDao.insert(model_Moscow);

        DataModel model_Minsk = new DataModel();
        model_Minsk.setId(2);
        model_Minsk.setLatitude("53.9");
        model_Minsk.setLongitude("27.56667");
        dataDao.insert(model_Minsk);

        DataModel model_Kiev = new DataModel();
        model_Kiev.setId(3);
        model_Kiev.setLatitude("50.431782");
        model_Kiev.setLongitude("30.516382");
        dataDao.insert(model_Kiev);
    }

    /**
     * Получение широты города из БД
     *
     * @param cityId id выбранного города
     * @return возвращаем широту
     */
    public double getLatLocation(int cityId) {
        databaseHelper = App.getInstance().getDatabaseInstance();
        double lat = Double.parseDouble(databaseHelper.getDataDao().getById(cityId).get(0).getLatitude());
        return lat;
    }

    /**
     * Получение долготы города из БД
     *
     * @param cityId id выбранного города
     * @return возвращаем долготу
     */
    public double getLngLocation(int cityId) {
        databaseHelper = App.getInstance().getDatabaseInstance();
        double lng = Double.parseDouble(databaseHelper.getDataDao().getById(cityId).get(0).getLongitude());
        return lng;
    }
}
