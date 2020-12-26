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
    void insert(TaraBD tara);

    @Update
    void update(TaraBD...tara);

    @Delete
    void delete(TaraBD...tara);



}
