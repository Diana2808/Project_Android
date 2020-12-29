package com.example.project.serviceSQLite;

import android.content.Context;

import com.example.project.asyncTask.AsyncTaskRunner;
import com.example.project.asyncTask.Callback;
import com.example.project.claseBD.MonedaBD;
import com.example.project.claseBD.TaraBD;
import com.example.project.dao.TaraDao;
import com.example.project.database.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class TaraService {

    private final TaraDao taraDao;
    private final AsyncTaskRunner taskRunner;

    public TaraService(Context context) {
        taraDao= DatabaseManager.getInstance(context).getTaraDao();
        taskRunner=new AsyncTaskRunner();
    }
    //INSERT

    public  void insert(Callback<TaraBD> callback, final TaraBD taraBD){
        Callable<TaraBD> callable=new Callable<TaraBD>() {
            @Override
            public TaraBD call() {
                if (taraBD == null) {
                    return null;
                }

                long id = taraDao.insert(taraBD);
                if(id==-1){
                    return null;

                }
                taraBD.setId(id);
                return taraBD;
            }

        };
        taskRunner.executeAsync(callable,callback);
    }


    //UPDATE

    public void update(final TaraBD taraBD, Callback<TaraBD> callback){
        Callable<TaraBD> callable=new Callable<TaraBD>() {
            @Override
            public TaraBD call() {
                if(taraBD ==null){
                    return null;

                }
                long numarTari=taraDao.update(taraBD);
                if(numarTari <1){
                    return null;

                }
                return taraBD;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    //DELETE

    public void delete(final TaraBD taraBD, Callback<Integer> callback){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() {
                if(taraBD ==null){
                    return -1;

                }
                return taraDao.delete(taraBD);


            }
        };
        taskRunner.executeAsync(callable,callback);
    }


    public void getAll(Callback<List<TaraBD>> callback){
        Callable<List<TaraBD>> callable=new Callable<List<TaraBD>>() {

            @Override
            public List<TaraBD> call() {
                return taraDao.getAllTara();

            }
        };

        taskRunner.executeAsync(callable,callback);
    }

}
