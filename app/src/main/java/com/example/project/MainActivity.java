package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project.clase.Tara;
import com.example.project.fragmente.BibliotecaFragment;
import com.example.project.fragmente.ColectieFragment;
import com.example.project.fragmente.AcasaFragment;
import com.example.project.fragmente.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //pentru menu bottomNavigationView
    BottomNavigationView bottomNavigationView;

    // fragmentul curent
    private Fragment fragmentCurent;

    //lista de tari cu monede JSON
    private ArrayList<Tara>  tari=new ArrayList<>();






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

                    fragmentCurent = BibliotecaFragment.newInstance(tari);
                }else if(item.getItemId()==R.id.fragment_acasa) {
                    fragmentCurent = new AcasaFragment();
                }else if(item.getItemId()==R.id.fragment_colectiaMea) {
                    fragmentCurent = new ColectieFragment();
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


}