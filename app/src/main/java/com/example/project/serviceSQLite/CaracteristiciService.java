package com.example.project.serviceSQLite;

import android.content.Context;

import com.example.project.asyncTask.AsyncTaskRunner;
import com.example.project.asyncTask.Callback;
import com.example.project.claseBD.CaracteristiciBD;
import com.example.project.claseBD.MonedaBD;
import com.example.project.dao.CaracteristiciDao;
import com.example.project.database.DatabaseManager;

import java.util.concurrent.Callable;

public class CaracteristiciService {

    //legatura cu baza de date
    private final CaracteristiciDao caracteristiciDao;
    //procesare paralela
    private final AsyncTaskRunner taskRunner;

    public CaracteristiciService(Context context) {
        this.caracteristiciDao = DatabaseManager.getInstance(context).getCaracteristiciDao();
        this.taskRunner = new AsyncTaskRunner();
    }


    //INSERT

    public  void insert(Callback<CaracteristiciBD> callback, final CaracteristiciBD caracteristiciBD){
        Callable<CaracteristiciBD> callable=new Callable<CaracteristiciBD>() {
            @Override
            public CaracteristiciBD call() {
                if (caracteristiciBD == null) {
                    return null;
                }

                long id = caracteristiciDao.insert(caracteristiciBD);
                if(id==-1){
                    return null;

                }
                caracteristiciBD.setId_caracteristici(id);
                return caracteristiciBD;
            }

        };
        taskRunner.executeAsync(callable,callback);
    }


    //UPDATE

    public void update(final CaracteristiciBD caracteristiciBD, Callback<CaracteristiciBD> callback){
        Callable<CaracteristiciBD> callable=new Callable<CaracteristiciBD>() {
            @Override
            public CaracteristiciBD call() {
                if(caracteristiciBD ==null){
                    return null;

                }
                long numarCaract=caracteristiciDao.update(caracteristiciBD);
                if(numarCaract <1){
                    return null;

                }
                return caracteristiciBD;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    //DELETE

    public void delete(final CaracteristiciBD caracteristiciBD, Callback<Integer> callback){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() {
                if(caracteristiciBD ==null){
                    return -1;

                }
                return caracteristiciDao.delete(caracteristiciBD);


            }
        };
        taskRunner.executeAsync(callable,callback);
    }



}
