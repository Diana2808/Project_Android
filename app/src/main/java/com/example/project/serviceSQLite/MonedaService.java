package com.example.project.serviceSQLite;

import android.content.Context;

import com.example.project.asyncTask.AsyncTaskRunner;
import com.example.project.asyncTask.Callback;
import com.example.project.clase.ListaMonedeTabele;
import com.example.project.clase.Tara;
import com.example.project.claseBD.CaracteristiciBD;
import com.example.project.claseBD.MonedaBD;
import com.example.project.claseBD.TaraBD;
import com.example.project.dao.CaracteristiciDao;
import com.example.project.dao.MonedaDao;
import com.example.project.dao.TaraDao;
import com.example.project.database.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class MonedaService {

    private final MonedaDao monedaDao;
    private final TaraDao taraDao;
    private final CaracteristiciDao caracteristiciDao;
    private final AsyncTaskRunner taskRunner;

    public MonedaService(Context context) {

        monedaDao= DatabaseManager.getInstance(context).getMonedaDao();
        taraDao= DatabaseManager.getInstance(context).getTaraDao();
        caracteristiciDao= DatabaseManager.getInstance(context).getCaracteristiciDao();
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
                moneda.setId(id);
                return moneda;
            }

        };
        //ce returneaza callable asta tb sa am in callback
        taskRunner.executeAsync(callable,callback);
    }

    public  void insertAll( Callback<MonedaBD> callbackM, final MonedaBD
            moneda, final TaraBD tara, final CaracteristiciBD carac){

        Callable<MonedaBD> callable=new Callable<MonedaBD>() {
            @Override
            public MonedaBD call() {
                if (moneda == null) {
                    return null;
                }

                long idTara = taraDao.insert(tara);
                long idCarac = caracteristiciDao.insert(carac);
                moneda.setId_tara(idTara);
                moneda.setId_caracteristici(idCarac);
                //daca id = -1 inseamna ca nu a mers insertul

                long id = monedaDao.insert(moneda);
                if(id==-1){
                    return null;

                }
                moneda.setId(id);

                return moneda;
            }

        };
        //ce returneaza callable asta tb sa am in callback
        taskRunner.executeAsync(callable,callbackM);
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

    public void delete(final List<ListaMonedeTabele> lista, final MonedaBD monedaBD, Callback<Integer> callback, final TaraBD tara, final CaracteristiciBD carac){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() {
                if(monedaBD ==null){
                    return -1;

                }


                long idTara = tara.getId();
                long idCarac =carac.getId();
                caracteristiciDao.delete(carac);
                taraDao.delete(tara);
                if(monedaBD.getId_tara()==idTara && monedaBD.getId_caracteristici()==idCarac){
                    return monedaDao.delete(monedaBD);
                }
               return -1;


            }
        };
        taskRunner.executeAsync(callable,callback);
    }



    //->>>>>>>>>>>>>>>>>>>>>>>
    public void getAll2(Callback<List<ListaMonedeTabele>> callback){
        Callable<List<ListaMonedeTabele>> callable=new Callable<List<ListaMonedeTabele>>() {

            @Override
            public List<ListaMonedeTabele> call() {

                return monedaDao.getAllImpreuna();

            }
        };

        taskRunner.executeAsync(callable,callback);

    }


    public void getAllAn(Callback<List<ListaMonedeTabele>> callback){
        Callable<List<ListaMonedeTabele>> callable=new Callable<List<ListaMonedeTabele>>() {

            @Override
            public List<ListaMonedeTabele> call() {

                return monedaDao.getMonedePeste2000();

            }
        };

        taskRunner.executeAsync(callable,callback);

    }


    public void getAllTara(Callback<List<ListaMonedeTabele>> callback){
        Callable<List<ListaMonedeTabele>> callable=new Callable<List<ListaMonedeTabele>>() {

            @Override
            public List<ListaMonedeTabele> call() {

                return monedaDao.getMonedeDupaTara();

            }
        };

        taskRunner.executeAsync(callable,callback);

    }


}
