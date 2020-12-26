package com.example.project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.project.claseBD.CaracteristiciBD;

@Dao
public interface CaracteristiciDao {

    @Insert
    void insert(CaracteristiciBD caracteristici);

    @Update
    void update(CaracteristiciBD caracteristici);

    @Delete
    void delete(CaracteristiciBD caracteristiciBD);
}
