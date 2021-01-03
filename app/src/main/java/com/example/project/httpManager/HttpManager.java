package com.example.project.httpManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpManager implements Callable<String> {


    private URL url;

    //conexiune
    private HttpURLConnection connection;

    //info citita
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    //url ca parametru de intrare
    private final String urlAdress;

    public HttpManager(String urlAdress) {
        this.urlAdress = urlAdress;
    }

    @Override
    public String call()  {
        try{
            //initializez url
            url=new URL(urlAdress);


            //conectiune
            connection= (HttpURLConnection) url.openConnection();

            //info culeaza
            inputStream=connection.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);


            StringBuilder result=new StringBuilder();
            String line;
            while ((line=bufferedReader.readLine())!=null){
                result.append(line);

            }

            //returnez rezultatul
            return result.toString();
        }



     catch (
    MalformedURLException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }finally {
        exceptiiProvocateLaCitire();
    }
        return null;    }

    private void exceptiiProvocateLaCitire() {
        //inchidem fiecare clasa utilitara
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //inchidem si connection
        connection.disconnect();
    }


}
