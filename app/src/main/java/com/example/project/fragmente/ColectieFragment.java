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
import com.example.project.MonedaAdapter;
import com.example.project.R;
import com.example.project.asyncTask.Callback;
import com.example.project.clase.Caracteristici;
import com.example.project.clase.ListaMonedeTabele;
import com.example.project.clase.Moneda;
import com.example.project.clase.Tara;
import com.example.project.claseBD.CaracteristiciBD;
import com.example.project.claseBD.MonedaBD;
import com.example.project.claseBD.TaraBD;
import com.example.project.serviceSQLite.CaracteristiciService;
import com.example.project.serviceSQLite.MonedaService;
import com.example.project.serviceSQLite.TaraService;

import java.util.ArrayList;
import java.util.List;


public class ColectieFragment extends Fragment {

 private Button btnAn;
 private Button btnTara;
 private Button btnAdauga;
 private ListView lvColectie;
 private List<Tara> listaTari=new ArrayList<>();
 public static final String CHEIE_1="ceva";
 private  List<ListaMonedeTabele> listaTotTabele=new ArrayList<>();


 public TaraService taraService;
 public CaracteristiciService caracteristiciService;
 public MonedaService monedaService;

   private List<MonedaBD> lm=new ArrayList<>();
   private List<TaraBD> lt=new ArrayList<>();
   private List<CaracteristiciBD> lc=new ArrayList<>();

   private boolean iAn=false;
   private boolean iTara=false;


    public ColectieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_colectie, container, false);
        lvColectie=view.findViewById(R.id.lv_colectie);
        btnAn =view.findViewById(R.id.btn_peste2000);
        btnTara=view.findViewById(R.id.btn_filtruPeRomania);
        btnAdauga=view.findViewById(R.id.btn_adauga);
        taraService=new TaraService(getContext().getApplicationContext());
        caracteristiciService=new CaracteristiciService(getContext().getApplicationContext());
        monedaService=new MonedaService(getContext().getApplicationContext());


        btnAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listaTari!=null && iAn==false){
                    listaTari.clear();
                    monedaService.getAllAn(callbackGetPeste2000());
                    listaTari = getArguments().getParcelableArrayList(CHEIE_1);
                    iAn=true;
                    btnAn.setText("Toata lista!");
                }else{

                    listaTari.clear();
                    monedaService.getAll2(callbackGetAll());
                    listaTari = getArguments().getParcelableArrayList(CHEIE_1);
                    iAn=false;
                    btnAn.setText("Peste anii 2000");

                }
            }
        });

        btnTara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaTari!=null && iTara==false){
                    listaTari.clear();
                    monedaService.getAllTara(callbackGetDupaTara());
                    listaTari = getArguments().getParcelableArrayList(CHEIE_1);
                    iTara=true;
                    btnTara.setText("Toata lista!");
                }else{

                    listaTari.clear();
                    monedaService.getAll2(callbackGetAll());
                    listaTari = getArguments().getParcelableArrayList(CHEIE_1);
                    iTara=false;
                    btnTara.setText("Monede din Romania");
                }
            }
        });


        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext().getApplicationContext(), AdaugInColectieActivity.class);
                startActivity(intent);
            }
        });

        if (getArguments() != null) {


            Toast.makeText(getContext().getApplicationContext(),listaTari.toString(),Toast.LENGTH_LONG).show();
            listaTari = getArguments().getParcelableArrayList(CHEIE_1);
            //POPULARE
            if(listaTari.size()==0){
                monedaService.getAll2(callbackGetAll());

            }



            Log.i("FRAGMENT COLECTIE", listaTari.toString());
        }
        //creare adapter pentru ListView
        if (getContext() != null) {

           MonedaAdapter adapter = new MonedaAdapter(getContext().getApplicationContext(),
                   R.layout.lv_row_view_biblioteca,
                    listaTari, getLayoutInflater());
            lvColectie.setAdapter(adapter);
            Log.i("dsfddddddd",listaTari.toString());
        }



        return  view;

    }



    //pentru ca informatia trebuie transferata prin activitatea
    // care contine fragmentul in care vrem sa afisam datele, avem nevoie de un newInstance
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




    private  Callback<List<ListaMonedeTabele>> callbackGetAll(){
        return new Callback<List<ListaMonedeTabele>>() {
            @Override
            public void runResultOnUiThread(List<ListaMonedeTabele> result) {
                if(result!=null){
                    listaTotTabele.clear();
                    listaTotTabele.addAll(result);
                    listaTari=transformInListaDeTariDinTabele(listaTotTabele);
                    notifyInternalAdapter();
                    Log.i("TEST: Lista", listaTotTabele.toString());

                }
            }
        };
    }




    private List<Tara> transformInListaDeTariDinTabele(List<ListaMonedeTabele> lista ){

        for(int i=0;i<lista.size();i++){

            String continent=lista.get(i).getContinent();
            String numaTara=lista.get(i).getDenumire_tara();

            String valoare=lista.get(i).getValoare();
            String denumireMoneda=lista.get(i).getDneumire_moneda();
            int an=lista.get(i).getAn();
            String diametru=lista.get(i).getDiametru();
            String grosime=lista.get(i).getGrosime();
            String culoare=lista.get(i).getCuloare();
            String material=lista.get(i).getMaterial();

            Caracteristici c=new Caracteristici(grosime,diametru,culoare,material);
            Moneda m=new Moneda(an,valoare,denumireMoneda,c);
            Tara t=new Tara(numaTara,continent,m);

            listaTari.add(t);

        }

        return listaTari;



    }



    private  Callback<List<ListaMonedeTabele>> callbackGetPeste2000(){
        return new Callback<List<ListaMonedeTabele>>() {
            @Override
            public void runResultOnUiThread(List<ListaMonedeTabele> result) {
                if(result!=null){
                    listaTotTabele.clear();
                    listaTotTabele.addAll(result);
                    listaTari=transformInListaDeTariDinTabele(listaTotTabele);
                    notifyInternalAdapter();
                    Log.i("TEST: Lista toate", listaTotTabele.toString());
                    Log.i("TEST: Lista tari", listaTotTabele.toString());

                }
            }
        };
    }



    private  Callback<List<ListaMonedeTabele>> callbackGetDupaTara(){
        return new Callback<List<ListaMonedeTabele>>() {
            @Override
            public void runResultOnUiThread(List<ListaMonedeTabele> result) {
                if(result!=null){
                    listaTotTabele.clear();
                    listaTotTabele.addAll(result);
                    listaTari=transformInListaDeTariDinTabele(listaTotTabele);
                    notifyInternalAdapter();
                    Log.i("TEST: Lista toate", listaTotTabele.toString());
                    Log.i("TEST: Lista tari", listaTotTabele.toString());

                }
            }
        };
    }
}