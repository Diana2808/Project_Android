package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.clase.Caracteristici;
import com.example.project.clase.Culoare;
import com.example.project.clase.Moneda;
import com.example.project.clase.Tara;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AdaugInColectieActivity extends AppCompatActivity {
    public static final String CHEIE_TARA="cheie";

    Spinner spinContinent;
    TextInputEditText tietTara;
    TextInputEditText tietDenumire;
    TextInputEditText tietValoare;
    TextInputEditText tietDiamentru;
    TextInputEditText tietGrosime;
    TextInputEditText tietMaterial;
    EditText etAn;
    RadioGroup rgCuloare;
    Button btnAdaug;

    //pentru transferul informatiilor inapoi
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaug_in_colectie);

        //initializare date
        initializare();

        //actiune pe buton
        btnAdaug.setOnClickListener(preluareInformatie());

        //pentru transferul datelor
        intent=getIntent();


    }

    private View.OnClickListener preluareInformatie() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validare()){
                    Tara tara=creezTara();
                    Toast.makeText(getApplicationContext(),
                            tara.toString(),
                            Toast.LENGTH_LONG)
                            .show();
                    intent.putExtra(CHEIE_TARA,tara);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        };
    }



    private void initializare() {
        spinContinent=findViewById(R.id.spin_continent);
        adaugareInSpinner();
        tietTara=findViewById(R.id.tiet_tara);
        tietDenumire=findViewById(R.id.tiet_denumire);
        tietValoare=findViewById(R.id.tiet_valoare);
        tietDiamentru=findViewById(R.id.tiet_diametru);
        tietGrosime=findViewById(R.id.tiet_grosime);
        tietMaterial=findViewById(R.id.tiet_material);
        rgCuloare=findViewById(R.id.rg_culoare);
        etAn=findViewById(R.id.etn_an);
        btnAdaug=findViewById(R.id.btn_adaug);
    }

    private void adaugareInSpinner() {
        ArrayAdapter<CharSequence> adapterSpinner=ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.continente,R.layout.support_simple_spinner_dropdown_item);
        spinContinent.setAdapter(adapterSpinner);
    }


    //verificari
    private boolean validare(){
        if(
                tietTara.getText() == null || tietTara.getText().toString().trim().length() < 3 ||
                tietDenumire.getText() == null || tietDenumire.getText().toString().trim().length() < 3 ||
                tietValoare.getText() == null || tietValoare.getText().toString().trim().length() < 3 ||
                tietMaterial.getText() == null || tietMaterial.getText().toString().trim().length() < 3 ){
            Toast.makeText(getApplicationContext(),
                    "Campurile trebuie sa contina minim 3 caractere!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        } if(etAn.getText() == null || Integer.parseInt(etAn.getText().toString().trim())<4
                ||Integer.parseInt(etAn.getText().toString())<1500  ||Integer.parseInt(etAn.getText().toString())>2020) {
            Toast.makeText(getApplicationContext(),
                    "Anul trebuie sa contina 4 cifre completate si sa se incadreze intre 1500 si 2020!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        if(tietDiamentru.getText().toString().substring(tietDiamentru.getText().toString().length()-2).equals("cm")==false
        ||
                Integer.parseInt(tietDiamentru.getText().toString().substring(0,1))>3){
            Toast.makeText(getApplicationContext(),
                    "Diametrul trebuie sa contina la sfarsit 'cm'! Dimesiunea mai mica de 3 trebuie introdusa la inceput!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;

        }
        if(tietGrosime.getText().toString().substring(tietGrosime.getText().toString().length()-2).equals("mm")==false
        || Integer.parseInt(tietGrosime.getText().toString().substring(0,1))>3){

            //daca ii dai doar litere iti va crapa, inca ma gandesc cum sa modific :/

            Toast.makeText(getApplicationContext(),
                    "Grosimea trebuie sa contina la sfarsit 'mm'! Grosimea mai mica de 3 trebuie introdusa la inceput!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;

        }
        if(rgCuloare.getCheckedRadioButtonId()!=R.id.rb_argintiu && rgCuloare.getCheckedRadioButtonId()!=R.id.rb_auriu){
            Toast.makeText(getApplicationContext(),
                    "Trebuie sa alegeti o culoare!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        return true;
    }


    //creez obiect
    protected Tara creezTara(){
        String continent=spinContinent.getSelectedItem().toString();
        String tara=tietTara.getText().toString();
        String denumire=tietDenumire.getText().toString();
        String valoare=tietValoare.getText().toString();
        String diametru=tietDiamentru.getText().toString();
        String grosime=tietGrosime.getText().toString();
        String material=tietMaterial.getText().toString();
        String culoare=String.valueOf(Culoare.AURIU);
        if(rgCuloare.getCheckedRadioButtonId()==R.id.rb_argintiu){
           culoare=String.valueOf(Culoare.ARGINTIU);
        }
        int an=Integer.parseInt(etAn.getText().toString());


        Caracteristici caracteristici=new Caracteristici(grosime,diametru,culoare,material);
        Moneda moneda=new Moneda(an,valoare,denumire,caracteristici);

        Tara taraObiect=new Tara(tara,continent,moneda);
        System.out.println(taraObiect.toString());
        return taraObiect;
    }
}