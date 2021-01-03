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


    public static final String MONEDE = "monede";
    public static final String AN = "an";
    public static final String VALOARE = "valoare";
    public static final String DENUMIRE = "denumire";
    public static final String CARACTERISTICI = "caracteristici";
    public static final String CULOARE = "culoare";
    public static final String GROSIME = "grosime";
    public static final String DIAMETRU = "diametru";
    public static final String MATERIAL = "material";
    public static final String DENUMIRE1 = "denumire";
    public static final String CONTINENT = "continent";

    public static List<Tara> fromJson(String json){
        if(json==null ||json.isEmpty()){
            return new ArrayList<>();

        }

        try {
            JSONArray arrayTari = new JSONArray(json);

            List<Tara> listaTari = new ArrayList<>();

            for (int i = 0; i < arrayTari.length(); i++) {

                JSONObject objectTara = arrayTari.getJSONObject(i);
                //obiectul moneda
                JSONObject objectMonede = objectTara.getJSONObject(MONEDE);


                int an = objectMonede.getInt(AN);
                String valoare = objectMonede.getString(VALOARE);
                String denumire = objectMonede.getString(DENUMIRE);

                JSONObject objectCaracteristici = objectMonede.getJSONObject(CARACTERISTICI);
                String culoare = objectCaracteristici.getString(CULOARE);
                String grosime = objectCaracteristici.getString(GROSIME);
                String diametru = objectCaracteristici.getString(DIAMETRU);
                String material = objectCaracteristici.getString(MATERIAL);
                String denumireTara = objectTara.getString(DENUMIRE1);
                String continent = objectTara.getString(CONTINENT);

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
