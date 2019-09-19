package com.example.testnumbuster.Model.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity
public class DataModel {

    @PrimaryKey
    private long id;
    private String latitude;
    private String longitude;
}
