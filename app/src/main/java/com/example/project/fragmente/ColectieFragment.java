package com.example.project.fragmente;

import android.content.Intent;
import android.os.Bundle;

import androidx.arch.core.executor.TaskExecutor;
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


    public ColectieFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_colectie, container, false);
        lvColectie=view.findViewById(R.id.lv_colectie);
        btnAdauga=view.findViewById(R.id.btn_adaugare);
        taraService=new TaraService(getContext().getApplicationContext());
        caracteristiciService=new CaracteristiciService(getContext().getApplicationContext());
        monedaService=new MonedaService(getContext().getApplicationContext());

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // -> buton nefunctional
             // Intent intent=new Intent(getContext().getApplicationContext(),AdaugInColectieActivity.class);
             // startActivityForResult(intent,COD);
            }
        });


        if (getArguments() != null) {

            //IA-MI DIN BAZA DE DATE  -> inca nu merge selectul, nu stiu de ce

                monedaService.getAll2(callbackGetAll());

            Toast.makeText(getContext().getApplicationContext(),listaTari.toString(),Toast.LENGTH_LONG).show();
            listaTari = getArguments().getParcelableArrayList(CHEIE_1);
            //INSERAREA IN BAZA DE DATE
            if(listaTari.size()>0){
                 sincronizareBazaDeDate(listaTari);

            }

            Log.i("FRAGMENT COLECTIE", listaTari.toString());
        }
        //creare adapter pentru ListView
        if (getContext() != null) {
           /// ArrayAdapter adapter=new ArrayAdapter(getContext().getApplicationContext(),
             //    android.R.layout.simple_list_item_1,listaTari);
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


    //INSERARE PROPRIUZISA
    //trec datele din lista de Tari in elemente de tip TaraBD si le inserez in bd
    private void sincronizareBazaDeDate(List<Tara> lista){


        for(int i=0;i<lista.size();i++){

            String continent=lista.get(i).getContinent();
            String denTara=lista.get(i).getDenumire();

            TaraBD t=new TaraBD(continent,denTara);



            String grosime=lista.get(i).getMonede().getCaracteristici().getGrosime();
            String diametru=lista.get(i).getMonede().getCaracteristici().getDiametru();
            String culoare=lista.get(i).getMonede().getCaracteristici().getCuloare();
            String material=lista.get(i).getMonede().getCaracteristici().getMaterial();

            CaracteristiciBD c=new CaracteristiciBD(grosime,diametru,culoare,material);



            int an=lista.get(i).getMonede().getAn();
            String valoare=lista.get(i).getMonede().getValoare();
            String denumireMoneda=lista.get(i).getMonede().getDenumire();

            MonedaBD m=new MonedaBD(an, valoare,denumireMoneda,0 ,0);

            //inserarea propriuzisa
            monedaService.insertAll(inserareInBDMCallback(),m,t,c);

        }



    }



    private void afisareDinBazaDeDate(){
        taraService.getAll(getAllTariCallBack());
        caracteristiciService.getAll(getAllCaracteristiciCallBack());
        monedaService.getAll(getAllMonedeCallBack());

        if(lm!=null && lc!=null && lt!=null){
            for(int i=0;i<lm.size();i++) {
                for(int j=0;j<lc.size();j++){
                    Log.i("IDurile C!:",String.valueOf(lc.get(j).getId()));

                    for(int k=0;k<lt.size();k++){
                        if(lm.get(i).getId_caracteristici()==lc.get(j).getId()
                                && lm.get(i).getId_tara()==lt.get(k).getId()){

                            String grosime=lc.get(j).getGrosime();
                            String diametru=lc.get(j).getDiametru();
                            String culoare=lc.get(j).getCuloare();
                            String material=lc.get(j).getMaterial();

                            Caracteristici c=new Caracteristici(grosime,diametru,culoare,material);

                            int an=lm.get(i).getAn();
                            String valoare=lm.get(i).getValoare();
                            String denumireMon=lm.get(i).getDenumire();

                            Moneda m=new Moneda(an,valoare,denumireMon,c);

                            String continent=lt.get(k).getContinent();
                            String dentara=lt.get(k).getDenumireTara();

                            Tara t=new Tara(dentara,continent,m);

                            listaTari.add(t);
                        }
                    }

                }


            }

        }

    }

    private Callback<List<MonedaBD>> getAllMonedeCallBack(){
        return new Callback<List<MonedaBD>>() {
            @Override
            public void runResultOnUiThread(List<MonedaBD> result) {
                if(result!=null){
                    lm.clear();
                    lm.addAll(result);
                    Log.i("CALLBACK AFISARE:",lm.toString());
                    }
                }
            };
        }


    private Callback<List<TaraBD>> getAllTariCallBack(){
        return new Callback<List<TaraBD>>() {
            @Override
            public void runResultOnUiThread(List<TaraBD> result) {
                if(result!=null){
                    lt.clear();
                    lt.addAll(result);
                    Log.i("CALLBACK AFISARE:",lt.toString());

                }
            }
        };
    }

    private Callback<List<CaracteristiciBD>> getAllCaracteristiciCallBack(){
        return new Callback<List<CaracteristiciBD>>() {
            @Override
            public void runResultOnUiThread(List<CaracteristiciBD> result) {
                if(result!=null){
                    lc.clear();
                    lc.addAll(result);
                    Log.i("CALLBACK AFISARE:",lc.toString());

                }
            }
        };
    }







    private  Callback<MonedaBD> inserareInBDMCallback(){
        return new Callback<MonedaBD>() {
            @Override
            public void runResultOnUiThread(MonedaBD result) {

                Log.i("CALLBACK INSERT " ,result.toString());
            }
        };
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




}