package com.example.project.asyncTask;

//1.


public interface Callback<R> {
    void runResultOnUiThread(R result);
}
