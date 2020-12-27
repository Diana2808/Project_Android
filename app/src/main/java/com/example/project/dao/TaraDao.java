package com.example.project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.project.claseBD.TaraBD;


@Dao
public interface TaraDao {

    @Insert
    long insert(TaraBD tara);

    @Update
    int update(TaraBD...tara);

    @Delete
    int delete(TaraBD...tara);



}
