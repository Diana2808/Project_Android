package com.example.project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.claseBD.MonedaBD;

import java.util.List;

@Dao
public interface MonedaDao {

    @Insert
    long insert(MonedaBD moneda);

    @Update
    int update(MonedaBD moneda);

    @Delete
    int delete(MonedaBD moneda);

    @Query("SELECT * FROM monede")
    List<MonedaBD> getToateMonedele();

}
