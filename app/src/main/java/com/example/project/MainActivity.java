package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.project.clase.Caracteristici;
import com.example.project.clase.Moneda;
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

                    Intent intent=new Intent(getApplicationContext(), AdaugInColectieActivity.class);
                    startActivityForResult(intent, COD);


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

//pentru transfer de date in fragmentul ColectieFragment

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==COD && resultCode==RESULT_OK && data!=null){
            Tara moneda=data.getParcelableExtra(AdaugInColectieActivity.CHEIE_TARA);

            if( moneda!=null && moneda.getMonede()!=null && moneda.getMonede().getCaracteristici()!=null)
            {
                monedeListaColectie.add(moneda);

                if(fragmentCurent instanceof ColectieFragment)
                {
                    ((ColectieFragment) fragmentCurent).notifyInternalAdapter();
                }
            }
        }
    }


    //menu2 -> optionMenu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.adaugare_bd ||item.getItemId()==R.id.stergere_bd ||item.getItemId()==R.id.modifica_bd){
                    Intent intent1=new Intent(getApplicationContext(),AdaugInColectieActivity.class);
                    startActivity(intent1);
        }
        if(item.getItemId()==R.id.info){
            Intent intent2=new Intent(getApplicationContext(),InfoActivity.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}