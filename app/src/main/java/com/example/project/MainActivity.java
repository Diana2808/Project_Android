package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.fragmente.BibliotecaFragment;
import com.example.project.fragmente.ColectieFragment;
import com.example.project.fragmente.AcasaFragment;
import com.example.project.fragmente.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom  nav

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentSelectat=null;

                switch (item.getItemId()){
                    case R.id.fragment_biblioteca:
                        fragmentSelectat=new BibliotecaFragment();
                        break;

                    case R.id.fragment_colectiaMea:
                        fragmentSelectat=new ColectieFragment();
                        break;
                    case R.id.fragment_Profil:
                        fragmentSelectat=new ProfilFragment();
                        break;
                    case R.id.fragment_acasa:
                        fragmentSelectat=new AcasaFragment();
                        break;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_layout,fragmentSelectat).commit();


                return true;
            }

        });


        // ca optiune default, vom face BibliotecaFragment

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new AcasaFragment()).commit();



    }

















}