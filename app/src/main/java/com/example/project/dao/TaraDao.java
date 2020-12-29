package com.example.project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.claseBD.TaraBD;

import java.util.List;


@Dao
public interface TaraDao {

    @Insert
    long insert(TaraBD tara);

    @Update
    int update(TaraBD...tara);

    @Delete
    int delete(TaraBD...tara);

    @Query("SELECT * FROM tari")
    List<TaraBD> getAllTara();

}
