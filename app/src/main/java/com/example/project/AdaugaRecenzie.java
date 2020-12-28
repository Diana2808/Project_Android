package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdaugaRecenzie extends AppCompatActivity {

    public static final String REVIEW_SHARED_PREF = "reviewSharedPref";
    public static final String NUME = "nume";
    public static final String DESCRIERE = "descriere";
    public static final String RATING = "rating";
    private TextInputEditText tietNume;
    private RatingBar ratingBar;
    private EditText etDescriere;
    private FloatingActionButton fabSave;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_recenzie);
        initializare();
    }

    private void initializare(){
        tietNume = findViewById(R.id.tiet_numeColectionar);
        ratingBar = findViewById(R.id.ratingBar_review);
        etDescriere = findViewById(R.id.editText_review);
        fabSave = findViewById(R.id.fab_saveReview);

        //construire fisier de pref
        preferences = getSharedPreferences(REVIEW_SHARED_PREF, MODE_PRIVATE);

        fabSave.setOnClickListener(salvareInSharedPreferences());
        incarcareDinSharedPref();
    }

    private View.OnClickListener salvareInSharedPreferences() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = tietNume.getText()!=null?tietNume.getText().toString():"";
                Float nrStelute = ratingBar.getRating();
                String descriere = etDescriere.getText()!=null?etDescriere.getText().toString():"";

                //salvare
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(NUME, nume);
                editor.putFloat(RATING, nrStelute);
                editor.putString(DESCRIERE, descriere);
                editor.apply();
                finish();
            }
        };
    }

    private void incarcareDinSharedPref() {
        String nume = preferences.getString(NUME, "");
        Float nrStelute = preferences.getFloat(RATING, 0);
        String descriere = preferences.getString(DESCRIERE, "");
        //incarcare valori din fisier in componentele vizuale
        tietNume.setText(nume);
        ratingBar.setRating(nrStelute);
        etDescriere.setText(descriere);
    }
}