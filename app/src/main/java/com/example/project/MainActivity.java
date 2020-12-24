package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.project.clase.Tara;
import com.example.project.fragmente.BibliotecaFragment;
import com.example.project.fragmente.ColectieFragment;
import com.example.project.fragmente.AcasaFragment;
import com.example.project.fragmente.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int COD = 210;
    //pentru menu bottomNavigationView
    BottomNavigationView bottomNavigationView;

    // fragmentul curent
    private Fragment fragmentCurent;

    //lista de tari cu monede JSON
    private ArrayList<Tara> tariListaJSON =new ArrayList<>();

    //lista de monede din colectie
    private ArrayList<Tara>  monedeListaColectie=new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializare();


    }


    //Bottom  nav
    private void initializare() {
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.fragment_biblioteca) {

                    fragmentCurent = BibliotecaFragment.newInstance(tariListaJSON);
                }else if(item.getItemId()==R.id.fragment_acasa) {
                    fragmentCurent = new AcasaFragment();
                }else if(item.getItemId()==R.id.fragment_colectiaMea) {
                    fragmentCurent = ColectieFragment.newInstance(monedeListaColectie);
                }else if(item.getItemId()==R.id.fragment_Profil) {
                    fragmentCurent = new ProfilFragment();
                }


                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_layout,fragmentCurent).commit();


                return true;
            }

        });


        // ca optiune default, vom face BibliotecaFragment

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new AcasaFragment()).commit();
    }



    //pentru transferul de informatii dintr-o alta ACTIVITATE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==COD && resultCode==RESULT_OK && data!=null){
            Tara monedaNoua=data.getParcelableExtra(AdaugInColectieActivity.CHEIE_TARA);
            if(monedaNoua!=null)
            {
                monedeListaColectie.add(monedaNoua);
                System.out.println(monedeListaColectie.toString());
                if(fragmentCurent instanceof ColectieFragment)
                {
                    ((ColectieFragment) fragmentCurent).notifyInternalAdapter();
                }
            }
        }
    }
}