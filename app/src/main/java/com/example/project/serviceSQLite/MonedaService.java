package com.example.project.serviceSQLite;

import android.content.Context;

import com.example.project.asyncTask.AsyncTaskRunner;
import com.example.project.asyncTask.Callback;
import com.example.project.claseBD.MonedaBD;
import com.example.project.dao.MonedaDao;
import com.example.project.database.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class MonedaService {

    private final MonedaDao monedaDao;
    private final AsyncTaskRunner taskRunner;

    public MonedaService(Context context) {

        monedaDao= DatabaseManager.getInstance(context).getMonedaDao();
        taskRunner=new AsyncTaskRunner();

    }


    //SELECT

    public void getAll(Callback<List<MonedaBD>> callback){
        Callable<List<MonedaBD>> callable=new Callable<List<MonedaBD>>() {
            //callablel returneaza din baza de date lista de obiecte

            @Override
            public List<MonedaBD> call() {
                return monedaDao.getToateMonedele();

            }
        };
        //callbackul ia lista si o proceseaza
        //callback = bucata din activitate
        taskRunner.executeAsync(callable,callback);
    }



    //INSERT

    public  void insert(Callback<MonedaBD> callback,final MonedaBD moneda){
        Callable<MonedaBD> callable=new Callable<MonedaBD>() {
            @Override
            public MonedaBD call() {
                if (moneda == null) {
                    return null;
                }

                //daca id = -1 inseamna ca nu a mers insertul
                long id = monedaDao.insert(moneda);
                if(id==-1){
                    return null;

                }
                moneda.setIdMoneda(id);
                return moneda;
            }

        };
        //ce returneaza callable asta tb sa am in callback
        taskRunner.executeAsync(callable,callback);
    }


    //UPDATE

    public void update(final MonedaBD monedaBD, Callback<MonedaBD> callback){
        Callable<MonedaBD> callable=new Callable<MonedaBD>() {
            @Override
            public MonedaBD call() {
                if(monedaBD ==null){
                    return null;

                }
                long numarMonede=monedaDao.update(monedaBD);
                if(numarMonede <1){
                    return null;

                }
                return monedaBD;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    //DELETE

    public void delete(final MonedaBD monedaBD, Callback<Integer> callback){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() {
                if(monedaBD ==null){
                    return -1;

                }
                return monedaDao.delete(monedaBD);


            }
        };
        taskRunner.executeAsync(callable,callback);
    }


}
