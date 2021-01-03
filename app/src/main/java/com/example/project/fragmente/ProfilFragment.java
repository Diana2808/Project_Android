package com.example.project.fragmente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.PrietenAdapter;
import com.example.project.R;
import com.example.project.asyncTask.Callback;
import com.example.project.clase.Prieten;
import com.example.project.firebase.FirebaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class ProfilFragment extends Fragment {

    public static final String NUMELE_TREBUIE_SA_CONTINA_MINIM_3_CARACTERE = "Numele trebuie sa contina minim 3 caractere!";
    public static final String PRENUMELE_TREBUIE_SA_CONTINA_MINIM_3_CARACTERE = "Prenumele trebuie sa contina minim 3 caractere!";
    public static final String NUMARUL_DE_MONEDE_POATE_FI_DOAR_POZITIV = "Numarul de monede poate fi doar pozitiv!";
    private EditText etNume;
    private TextInputEditText tietPrenume;
    private EditText etMonede;
    private ListView lvPrieteni;
    private FloatingActionButton fabStergereCampuri;
    private FloatingActionButton fabStergereElement;
    private FloatingActionButton fabAdaugare;

    private List<Prieten> lista= new ArrayList<>();

    //pentru Firebase
    private FirebaseService firebaseService;
    private int idPrietenSelectat = -1;


    public ProfilFragment() {

    }


    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_profil, container, false);

        initializare(view);
        fabAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validari()) {
                    Prieten prieten = crearePrieten();
                    firebaseService.upsert(prieten);
                }
            }
        });
        fabStergereElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idPrietenSelectat!=-1){
                    firebaseService.delete(lista.get(idPrietenSelectat));
                }
            }
        });

        fabStergereCampuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curatCampurile();
            }
        });

        lvPrieteni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idPrietenSelectat=position;
                etNume.setText(lista.get(idPrietenSelectat).getNume());
                tietPrenume.setText(lista.get(idPrietenSelectat).getPrenume());
                etMonede.setText(String.valueOf(lista.get(idPrietenSelectat).getNumarMonede()));

            }
        });
        setareAdapter();

        firebaseService = FirebaseService.newInstance();
        firebaseService.atasare(afisareCallback());

        return view;
    }

    private void initializare(View view) {
        etNume=view.findViewById(R.id.ettpn_nume);
        tietPrenume=view.findViewById(R.id.tiet_prenume);
        etMonede=view.findViewById(R.id.etn_numar_monede);
        lvPrieteni=view.findViewById(R.id.lv_prieteni);
        fabStergereCampuri=view.findViewById(R.id.fab_StergereFields);
        fabStergereElement=view.findViewById(R.id.fab_Stergere);
        fabAdaugare=view.findViewById(R.id.fab_AdaugFB);
    }

    private Prieten crearePrieten(){
        String id;
       if(idPrietenSelectat>=0){
           id=lista.get(idPrietenSelectat).getId();
       }else  id=null;
       String nume =etNume.getText().toString();
       String prenume=tietPrenume.getText().toString();
       int nrMonede=Integer.parseInt(etMonede.getText().toString());
       Prieten p=new Prieten(id,nume,prenume,nrMonede);

       return p;
    }

    private Callback<List<Prieten>> afisareCallback(){
        return new Callback<List<Prieten>>() {
            @Override
            public void runResultOnUiThread(List<Prieten> result) {
                if(result!=null){
                    lista.clear();
                    lista.addAll(result);
                    notifyAdapter();
                    curatCampurile();
                }
            }
        };
    }


    //notific adapterul
    private void notifyAdapter(){
        PrietenAdapter adapter=(PrietenAdapter)lvPrieteni.getAdapter();
        adapter.notifyDataSetChanged();
    }


    private  void  curatCampurile(){
        etNume.setText(null);
        tietPrenume.setText(null);
        etMonede.setText(null);
        idPrietenSelectat=-1;
    }



    //Setez pe listview Adapterul
    private void setareAdapter() {
        PrietenAdapter adapter = new PrietenAdapter(getContext().getApplicationContext(), R.layout.row_prieteni,
                lista, getLayoutInflater());
        lvPrieteni.setAdapter(adapter);
    }


    //validari pe campuri

    private boolean validari() {
        if (etNume.getText() == null || etNume.getText().toString().trim().isEmpty() ||etNume.getText().toString().trim().length()<3) {
            Toast.makeText(getContext().getApplicationContext(),
                    NUMELE_TREBUIE_SA_CONTINA_MINIM_3_CARACTERE,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietPrenume.getText() == null || tietPrenume.getText().toString().trim().isEmpty() ||tietPrenume.getText().toString().trim().length()<3) {
            Toast.makeText(getContext().getApplicationContext(),
                    PRENUMELE_TREBUIE_SA_CONTINA_MINIM_3_CARACTERE,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(Integer.parseInt(etMonede.getText().toString())<0){
            Toast.makeText(getContext().getApplicationContext(),
                    NUMARUL_DE_MONEDE_POATE_FI_DOAR_POZITIV,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}