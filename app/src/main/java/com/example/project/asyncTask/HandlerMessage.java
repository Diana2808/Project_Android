package com.example.project.asyncTask;


//2.


public class HandlerMessage<R> implements Runnable {

        //ne asiguram ca dupa ce am creeat un mesaj cu un
        // resultat si un thread nu ii mai permitem sa isi schimbe REFERINTA
    private final R result;
    private final Callback<R> mainThreadOperation;

    public HandlerMessage(R result, Callback<R> mainThreadOperation) {
        this.result = result;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        mainThreadOperation.runResultOnUiThread(result);
    }
}
