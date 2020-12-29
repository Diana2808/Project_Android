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

    //pattern -> pentru a creea o singura instanta pt baza de date in tot proiectul -> SINGLETON
    //conexiuni multiple=> desincronizarea bazei de date
    //constructorul clasei provat pt a nu avea acces la creearea instantelor
    //dar avem clasa abstracta => NU are constructor


    //singura instanta din tot proiectul pe care o vom folosi
    //obiectul este static pentru a-l putea folosi in metoda statica
    //pentru ca nu depinde de un obiect creeat
    private static  DatabaseManager bazadedate;



    //deschide CONEXIUNI
    //metoda de initializare a acestei instante

    public static  DatabaseManager getInstance(Context context){

        //sa obitin obiectul fara a creea o noua instanta ( pentru ca evit desincronizarea si pt ca e o clasa abst)
        //contextul il avem in Activitate
        //context prin parametru -> din exterior

        if(bazadedate==null){
            //pentru atunci cand intra in metoda de doua ori, pe al doilea sa il blocheze
            synchronized (DatabaseManager.class){
                if(bazadedate==null){
                    bazadedate= Room.databaseBuilder(context,DatabaseManager.class,"ColectieMonedeCuSucces")
                            .fallbackToDestructiveMigration()
                            //fallbackToDestructiveMigration()
                            //-> cand creem o bd si vrem sa ne intorcem
                            // sa mai adaugam o coloana,
                            //schimb dimensiunea unei coloane,
                            //Room incearca sa migreze toate datele
                            //iar fallbackToDestructiveMigration() pt cand nu reuseste sa faca migrarile
                            //sterge inregistrarile
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
