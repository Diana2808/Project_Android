package com.example.project.fragmente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.project.AdaugaRecenzie;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AcasaFragment extends Fragment {

    public static final int REVIEW_REQUEST_CODE = 222;
    private FloatingActionButton fabReview;
    private TextView tvMesaj;
    private RatingBar ratingStelute;
    private EditText etDescriere;

    public AcasaFragment() {
        // Required empty public constructor
    }

    public static AcasaFragment newInstance(String param1, String param2) {
        AcasaFragment fragment = new AcasaFragment();
        Bundle bundle = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acasa, container, false);
        initializare(view);
        afiseazaMesaj();
        return view;
    }

    private void initializare(View view) {
        fabReview = view.findViewById(R.id.fab_parere);
        tvMesaj = view.findViewById(R.id.tv1_acasa);
        ratingStelute = view.findViewById(R.id.ratingBar);
        etDescriere = view.findViewById(R.id.editTextParere);
        fabReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), AdaugaRecenzie.class);
                startActivityForResult(intent, REVIEW_REQUEST_CODE);
            }
        });
    }

    //realizam popularea unui textView cu date din fisierul de preferinte


    private void afiseazaMesaj(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences(AdaugaRecenzie.REVIEW_SHARED_PREF,
                Context.MODE_PRIVATE);
        String nume = preferences.getString(AdaugaRecenzie.NUME, "");
        if(nume != null && !nume.isEmpty()){
            tvMesaj.setText(getString(R.string.acasa_mesaj_intampinare, nume));
        }
        etDescriere.setText(preferences.getString(AdaugaRecenzie.DESCRIERE, ""));
        ratingStelute.setRating(preferences.getFloat(AdaugaRecenzie.RATING, 0));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REVIEW_REQUEST_CODE) {
            afiseazaMesaj();
        }
    }
}