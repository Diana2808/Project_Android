package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.clase.Moneda;
import com.example.project.clase.Tara;

import java.util.ArrayList;
import java.util.List;

public class MonedaAdapter extends ArrayAdapter<Tara> {

    private Context context;
    private int resource;
    private List<Tara> tari;
    private LayoutInflater inflater;

    public MonedaAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Tara> objects,
                         LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.tari = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Tara tara = tari.get(position);
        if(tara != null) {
            addDenumireTara(view, tara.getDenumire());
            addContinent(view, tara.getContinent());
            addDenumireMoneda(view,tara.getMonede().getDenumire());
            addAnMoneda(view,tara.getMonede().getAn());
            addValoareMoneda(view,tara.getMonede().getValoare());
            addCuloare(view,tara.getMonede().getCaracteristici().getCuloare());
            addDiametru(view,tara.getMonede().getCaracteristici().getDiametru());
            addGrosime(view,tara.getMonede().getCaracteristici().getGrosime());
            addMaterial(view,tara.getMonede().getCaracteristici().getMaterial());

        }
        return view;
    }








    private void addDenumireTara(View view, String denumireTara){
        TextView textView = view.findViewById(R.id.tv_row_tara);
        if(denumireTara!=null && !denumireTara.isEmpty()){
            textView.setText(denumireTara);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }

    private void addContinent(View view, String continent){
        TextView textView = view.findViewById(R.id.tv_row_continent);
        if(continent!=null && !continent.isEmpty()){
            textView.setText(continent);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }

    private void addDenumireMoneda(View view, String denumireMoneda){
        TextView textView = view.findViewById(R.id.tv_row_denumire);
        if(denumireMoneda!=null && !denumireMoneda.isEmpty()){
            textView.setText(denumireMoneda);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }

    private void addValoareMoneda(View view, String valoare){
        TextView textView = view.findViewById(R.id.tv_row_valoare);
        if(valoare!=null && !valoare.isEmpty()){
            textView.setText(valoare);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }


    private void addAnMoneda(View view, int an){
        TextView textView = view.findViewById(R.id.tv_row_an);
        if(an>0000 && an<9999){
            textView.setText(String.valueOf(an));
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }


    private void addCuloare(View view, String culoare){
        TextView textView = view.findViewById(R.id.tv_row_culoare);
        if(culoare!=null && !culoare.isEmpty()){
            textView.setText(culoare);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }

    private void addDiametru(View view, String diametru){
        TextView textView = view.findViewById(R.id.tv_row_diametru);
        if(diametru!=null && !diametru.isEmpty()){
            textView.setText(diametru);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }


    private void addGrosime(View view, String grosime){
        TextView textView = view.findViewById(R.id.tv_row_grosime);
        if(grosime!=null && !grosime.isEmpty()){
            textView.setText(grosime);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }



    private void addMaterial(View view, String material){
        TextView textView = view.findViewById(R.id.tv_row_material);
        if(material!=null && !material.isEmpty()){
            textView.setText(material);
        } else {
            textView.setText(R.string.lv_row_default);
        }
    }

}
