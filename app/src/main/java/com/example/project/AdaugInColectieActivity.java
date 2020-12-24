package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.project.clase.Caracteristici;
import com.example.project.clase.Culoare;
import com.example.project.clase.Moneda;
import com.example.project.clase.Tara;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AdaugInColectieActivity extends AppCompatActivity {

    TextInputEditText tietContinent;
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
    public static final String CHEIE_TARA="cheie";




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
        tietContinent=findViewById(R.id.tiet_continent);
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


    //verificari
    private boolean validare(){
        if(tietContinent.getText() == null || tietContinent.getText().toString().trim().length() < 3 ||
                tietTara.getText() == null || tietTara.getText().toString().trim().length() < 3 ||
                tietDenumire.getText() == null || tietDenumire.getText().toString().trim().length() < 3 ||
                tietValoare.getText() == null || tietValoare.getText().toString().trim().length() < 3 ||
                tietDiamentru.getText() == null || tietDiamentru.getText().toString().trim().length() < 3 ||
                tietGrosime.getText() == null || tietGrosime.getText().toString().trim().length() < 3 ||
                tietMaterial.getText() == null || tietMaterial.getText().toString().trim().length() < 3 ){
            Toast.makeText(getApplicationContext(),
                    "Nu ati completat corect datele!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if(etAn.getText() == null || Integer.parseInt(etAn.getText().toString().trim())<4
        ) {
            Toast.makeText(getApplicationContext(),
                   "Anul trebuie sa contina 4 cifre completate!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }


    //creez obiect
    protected Tara creezTara(){
        String continent=tietContinent.getText().toString();
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