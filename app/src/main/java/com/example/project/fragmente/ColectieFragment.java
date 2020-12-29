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
 private List<TaraBD> listaTariBD=new ArrayList<>();
 public static final String CHEIE_1="ceva";

 public TaraService taraService;
 public CaracteristiciService caracteristiciService;
 public MonedaService monedaService;

    private  long indexCaract=-1;
    private  long indexTara=-1;




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

            //IA-MI DIN BAZA DE DATE

            listaTari = getArguments().getParcelableArrayList(CHEIE_1);
            ///AICI TREBUIE SA APELAM INSERARE IN BAZA DE DATE

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


    private void sincronizareBazaDeDate(List<Tara> lista){


        for(int i=0;i<lista.size();i++){
            String continent=lista.get(i).getContinent();
            String denTara=lista.get(i).getDenumire();


            TaraBD t=new TaraBD(continent,denTara);
            taraService.insert(inserareInBDTCallback(), t);

            String grosime=lista.get(i).getMonede().getCaracteristici().getGrosime();
            String diametru=lista.get(i).getMonede().getCaracteristici().getDiametru();
            String culoare=lista.get(i).getMonede().getCaracteristici().getCuloare();
            String material=lista.get(i).getMonede().getCaracteristici().getMaterial();


            CaracteristiciBD c=new CaracteristiciBD(grosime,diametru,culoare,material);
            caracteristiciService.insert(inserareInBDCCallback(),c);

            int an=lista.get(i).getMonede().getAn();
            String valoare=lista.get(i).getMonede().getValoare();
            String denumireMoneda=lista.get(i).getMonede().getDenumire();
            Toast.makeText(getContext().getApplicationContext(),String.valueOf(indexTara)+String.valueOf(indexCaract),
                    Toast.LENGTH_SHORT).show();
            MonedaBD m=new MonedaBD(an, valoare,denumireMoneda,indexTara,indexCaract);

            monedaService.insert(inserareInBDMCallback(),m);
        }


    }


    private Callback<TaraBD> inserareInBDTCallback(){
        return new Callback<TaraBD>() {
            @Override
            public void runResultOnUiThread(TaraBD result) {
              /*  if(result!=null){
                }*/

                indexTara=result.getId_tara();



            }
        };
    }


    private  Callback<CaracteristiciBD> inserareInBDCCallback(){
        return new Callback<CaracteristiciBD>() {
            @Override
            public void runResultOnUiThread(CaracteristiciBD result) {
              /*  if(result!=null){
                }*/

                indexCaract=result.getId_caracteristici();

                Log.i("CALLBACK INSERT " ,result.toString());

            }
        };
    }

    private  Callback<MonedaBD> inserareInBDMCallback(){
        return new Callback<MonedaBD>() {
            @Override
            public void runResultOnUiThread(MonedaBD result) {
              /*  if(result!=null){
                }*/

                Log.i("CALLBACK INSERT " ,result.toString());
            }
        };
    }






}