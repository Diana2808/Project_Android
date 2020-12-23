package com.example.project.fragmente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.AdaugInColectieActivity;
import com.example.project.R;
import com.example.project.clase.Tara;

import java.util.ArrayList;
import java.util.List;


public class ColectieFragment extends Fragment {


 private Button btnAdauga;
 private ListView lvColectie;
 private List<Tara> listaTari=new ArrayList<>();
 public static final int COD=222;
 public static final String CHEIE_1="ceva";


    public ColectieFragment() {

    }


    public static ColectieFragment newInstance(String param1, String param2) {
        ColectieFragment fragment = new ColectieFragment();
        Bundle bundle = new Bundle();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_colectie, container, false);
        btnAdauga=view.findViewById(R.id.btn_adaugare);
        lvColectie=view.findViewById(R.id.lv_colectie);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getContext().getApplicationContext(),AdaugInColectieActivity.class);
              startActivityForResult(intent,COD);
            }
        });


        if (getArguments() != null) {
            listaTari = getArguments().getParcelableArrayList(CHEIE_1);
            Log.i("FRAGMENT COLECTIE", listaTari.toString());
        }
        //creare adapter pentru ListView
        if (getContext() != null) {
            ArrayAdapter adapter=new ArrayAdapter(getContext().getApplicationContext(),android.R.layout.simple_list_item_1,listaTari);
            lvColectie.setAdapter(adapter);
        }


        return  view;

    }


    //pentru ca informatia trebuie transferata prin activitatea care contine fragmentul in care vrem sa afisam datele, avem nevoie de un newInstance
    public static ColectieFragment newInstance(ArrayList<Tara> lista){
        ColectieFragment fragment=new ColectieFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(ColectieFragment.CHEIE_1,lista);
        fragment.setArguments(bundle);
        return fragment;
    }



    public void notifyInternalAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvColectie.getAdapter();
        adapter.notifyDataSetChanged();
    }



}