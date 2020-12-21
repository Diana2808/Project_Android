package com.example.project.asyncTask;


//4.

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AsyncTaskRunner {

        //se initializeaz aprintr-o metoda din Executors
        //newCachedThreadPool -> determina cate treduri putem procesa in sincron
        //Executorul decide cand sa dea drumul

        //mai avem un handler care se initializeaza prin construct
        //Looper.getMainLooper() ->reprezinta o referinta catre thread principal
        //pentru a verifica daca pot sau nu sa trimit info prin Executor


    private final Executor executor= Executors.newCachedThreadPool();
    private final Handler handler=new Handler(Looper.getMainLooper());


        //creem metoda executeasync
        //care este apelata din activitate sa ne trimita rezultate
        //callable -> operatia async
        //callback -> zona din activitate unde vreau sa pun rezultatul

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOption){

            //PORNESTE UN NOU TASK
            //handler, rezutlatul , unde sa trimiti

        try{
            executor.execute(new RunnableTask<>(handler,asyncOperation,mainThreadOption));

        } catch (Exception e) {
            Log.i("AsyncTaskRunner","failed call executeAsync "+e.getMessage());
        }

    }


}
