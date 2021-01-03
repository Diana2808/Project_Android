package com.example.project.asyncTask;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;


//3.

public class RunnableTask<R> implements Runnable  {

    public static final String RUNNABLE_TASK = "RunnableTask";
    public static final String FAILED_CALL_RUNNABLE_TASK = "failed call runnableTask";
    private final Handler handler;
    private final Callable<R> asyncOption;
    private final Callback<R> mainThreadOperation;




    public RunnableTask(Handler handler, Callable<R> asyncOption, Callback<R> mainThreadOperation) {
        this.handler = handler;
        this.asyncOption = asyncOption;
        this.mainThreadOperation = mainThreadOperation;
    }


    @Override
    public void run() {
        try {
                //punem mana pe rezultat
            final R result=asyncOption.call();
                //il trimit catre handler -> il arunca el catre app cand doreste
            handler.post(new HandlerMessage<>(result,mainThreadOperation));
        } catch (Exception e) {
            Log.i(RUNNABLE_TASK, FAILED_CALL_RUNNABLE_TASK + e.getMessage());
        }

    }
}
