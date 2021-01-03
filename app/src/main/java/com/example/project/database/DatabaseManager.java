package com.example.project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project.claseBD.CaracteristiciBD;
import com.example.project.claseBD.MonedaBD;
import com.example.project.claseBD.TaraBD;
import com.example.project.dao.CaracteristiciDao;
import com.example.project.dao.MonedaDao;
import com.example.project.dao.TaraDao;

@Database(entities = {TaraBD.class, MonedaBD.class, CaracteristiciBD.class},version = 1,exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {

    //o singura conexiune in tot proiectul la baza asta de date

    private static  DatabaseManager bazadedate;


    //deschid CONEXIUNI
    //metoda de initializare a acestei instante

    public static  DatabaseManager getInstance(Context context){



        if(bazadedate==null){
            synchronized (DatabaseManager.class){
                if(bazadedate==null){
                    bazadedate= Room.databaseBuilder(context,DatabaseManager.class,"C&D")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }


        return bazadedate;
    }


    public abstract TaraDao getTaraDao();
    public abstract MonedaDao getMonedaDao();
    public abstract CaracteristiciDao getCaracteristiciDao();
}
