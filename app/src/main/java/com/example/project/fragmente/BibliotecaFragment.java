package com.example.project.fragmente;

import android.net.DnsResolver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.MonedaAdapter;
import com.example.project.R;
import com.example.project.asyncTask.AsyncTaskRunner;
import com.example.project.asyncTask.Callback;
import com.example.project.clase.Tara;
import com.example.project.httpManager.HttpManager;
import com.example.project.httpManager.TaraParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class BibliotecaFragment extends Fragment {


    //cheie
    private static final String BIBLIOTECA_CHEIE="cheieBiblioteca";

    //URL din care preiau datele
    private static final String URL="https://api.mocki.io/v1/7bc35419";

    //lista in care pun datele
    private List<Tara> listBiblioteca=new ArrayList<>();

    //elementul din layout unde adaug lista mea
    private ListView listView;

    private AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();


    public BibliotecaFragment() {

    }


    public static BibliotecaFragment newInstance(ArrayList<Tara> tari) {
        BibliotecaFragment fragment = new BibliotecaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BibliotecaFragment.BIBLIOTECA_CHEIE,tari);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_biblioteca, container, false);
        listView=view.findViewById(R.id.lvTari);

        if(getArguments()!=null){
            listBiblioteca=getArguments().getParcelableArrayList(BIBLIOTECA_CHEIE);
            Log.i("Fragment Biblioteca", listBiblioteca.toString());
        }

        //creare adapter pt listview
        if(getContext()!=null){
            Toast.makeText(getContext().getApplicationContext(),
                    "Lista cu toate monedele din lume!",Toast.LENGTH_SHORT).show();

            MonedaAdapter adapter = new MonedaAdapter(getContext().getApplicationContext(),
                                                R.layout.lv_row_view_biblioteca,
                                                listBiblioteca, getLayoutInflater());
            listView.setAdapter(adapter);
        }
        if(listBiblioteca.size()==0){
            getTariFromNetwork();
        }

        return view;
    }


    public void notifyInternalAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        adapter.notifyDataSetChanged();
    }


    private void getTariFromNetwork(){


        final Callable<String> asyncOperation= new HttpManager(URL);
       Callback<String> mainThreadOperation= new Callback<String>()

        {
            @Override
            public void runResultOnUiThread(String result) {

                //8.
                listBiblioteca.addAll(TaraParser.fromJson(result));

                //notofica adapterul
                notifyInternalAdapter();
            }
        };

        asyncTaskRunner.executeAsync(asyncOperation,mainThreadOperation);

    }

}