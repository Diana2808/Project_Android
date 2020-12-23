package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowAnimationFrameStats;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.clase.Tara;

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
            addDenumireTara(view, tara.getDenumire_tara());
            addContinent(view, tara.getContinent());
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

}
