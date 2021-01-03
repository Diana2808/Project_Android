package com.example.project.asyncTask;


//4.

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AsyncTaskRunner {


    public static final String ASYNC_TASK_RUNNER = "AsyncTaskRunner";
    public static final String FAILED_CALL_EXECUTE_ASYNC = "failed call executeAsync ";
    private final Executor executor= Executors.newCachedThreadPool();
    private final Handler handler=new Handler(Looper.getMainLooper());




    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOption){


        try{
            executor.execute(new RunnableTask<>(handler,asyncOperation,mainThreadOption));

        } catch (Exception e) {
            Log.i(ASYNC_TASK_RUNNER, FAILED_CALL_EXECUTE_ASYNC +e.getMessage());
        }

    }


}
