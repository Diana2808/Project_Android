package com.example.project.httpManager;

import android.util.Log;
import android.widget.Toast;

import com.example.project.clase.Caracteristici;
import com.example.project.clase.Moneda;
import com.example.project.clase.Tara;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class TaraParser {


    public static List<Tara> fromJson(String json){
        if(json==null ||json.isEmpty()){
            return new ArrayList<>();

        }

        try {
            JSONArray arrayTari= new JSONArray(json);

            List<Tara> listaTari=new ArrayList<>();

            for(int i=0;i<arrayTari.length();i++){

                JSONObject objectTara = arrayTari.getJSONObject(i);

                //obiectul tara
                JSONArray arrayMonede=objectTara.getJSONArray("monede");

                List<Moneda> listamonede=new ArrayList<>();



                for(int j=0;j<arrayMonede.length();j++){

                    JSONObject objectMoneda = arrayMonede.getJSONObject(j);
                    int an=objectMoneda.getInt("an");
                    String valoare=objectMoneda.getString("valoare");
                    String denumire=objectMoneda.getString("denumire");

                    JSONObject objectCaracteristici=objectMoneda.getJSONObject("caracteristici");
                    String culoare=objectCaracteristici.getString("culoare");
                    String grosime=objectCaracteristici.getString("grosime");
                    String diametru=objectCaracteristici.getString("diametru");
                    String material=objectCaracteristici.getString("material");

                    Caracteristici caracteristici=new Caracteristici(grosime,diametru,culoare,material);
                    Moneda moneda=new Moneda(an,valoare,denumire,caracteristici);

                    //adaug in lista de monede
                    listamonede.add(moneda);
                }


                String denumireTara=objectTara.getString("denumire");
                String continent=objectTara.getString("continent");


                Tara tara=new Tara(denumireTara,continent,listamonede);

                //adaug in lista de tari
                listaTari.add(tara);


            }
            return listaTari;



        } catch (JSONException e) {
            System.out.println("AOLEEEEEEEEEEEEEEEEEEEEEEEEEEU");
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
