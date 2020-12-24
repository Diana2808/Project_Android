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
            JSONArray arrayTari = new JSONArray(json);

            List<Tara> listaTari = new ArrayList<>();

            for (int i = 0; i < arrayTari.length(); i++) {

                JSONObject objectTara = arrayTari.getJSONObject(i);
                Log.i("fbvcvbdx",objectTara.toString());
                //obiectul moneda
                JSONObject objectMonede = objectTara.getJSONObject("monede");


                int an = objectMonede.getInt("an");
                String valoare = objectMonede.getString("valoare");
                String denumire = objectMonede.getString("denumire");

                JSONObject objectCaracteristici = objectMonede.getJSONObject("caracteristici");
                String culoare = objectCaracteristici.getString("culoare");
                String grosime = objectCaracteristici.getString("grosime");
                String diametru = objectCaracteristici.getString("diametru");
                String material = objectCaracteristici.getString("material");
                String denumireTara = objectTara.getString("denumire");
                String continent = objectTara.getString("continent");

                Caracteristici caracteristici = new Caracteristici(grosime, diametru, culoare, material);
                Moneda moneda = new Moneda(an, valoare, denumire, caracteristici);
                Tara tara = new Tara(denumireTara, continent,moneda );

                //adaug in lista de tari
                listaTari.add(tara);


            }

            return listaTari;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();

    }
}
