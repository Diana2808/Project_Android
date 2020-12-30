package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.clase.Prieten;
import com.example.project.clase.Tara;

import java.util.List;

public class PrietenAdapter extends ArrayAdapter<Prieten> {

    private Context context;
    private int resource;
    private List<Prieten> prieteni;
    private LayoutInflater inflater;

    public PrietenAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Prieten> objects,
                         LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.prieteni = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Prieten prieten=prieteni.get(position);

        if(prieten!=null){
            addName(view,prieten.getNume());
            addPrenume(view,prieten.getPrenume());
            addMonede(view,prieten.getNumarMonede());

        }

        return view;
    }

    private void addName(View view, String name) {
        TextView textView = view.findViewById(R.id.tv_numeAdap);
        populezTextViewContent(textView, name);
    }

    private void addPrenume(View view, String prenume) {
        TextView textView = view.findViewById(R.id.tv_prenumeAdap);
        populezTextViewContent(textView, prenume);
    }

    private void addMonede(View view, int nrMonede) {
        TextView textView = view.findViewById(R.id.tv_nrMonedeAdap);
        populezTextViewContentInt(textView, nrMonede);
    }

    private void populezTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("-||-");
        }
    }

    private void populezTextViewContentInt(TextView textView, int value) {
        if (value>=0) {
            textView.setText(String.valueOf(value));
        } else {
            textView.setText("-||-");
        }
    }


}
