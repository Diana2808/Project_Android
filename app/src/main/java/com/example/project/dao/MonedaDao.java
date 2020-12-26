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
    void insert(MonedaBD moneda);

    @Update
    void update(MonedaBD moneda);

    @Delete
    void delete(MonedaBD moneda);

    @Query("SELECT * FROM monede")
    List<MonedaBD> getToateMonedele();

    @Query("SELECT * FROM monede WHERE taraId=:taraId AND caracteristiciId=:caracteristiciId")
    List<MonedaBD> findRepositoriesForUser(final long taraId,final  long caracteristiciId );
}
