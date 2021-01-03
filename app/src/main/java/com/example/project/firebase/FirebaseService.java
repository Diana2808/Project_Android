package com.example.project.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project.asyncTask.Callback;
import com.example.project.clase.Prieten;
import com.example.project.database.DatabaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    public static final String FIREBASE_DO = "FIREBASE DO:";
    public static final String MERGEEE = "MERGEEE !";
    public static final String FIREBASE_FAIL = "FIREBASE FAIL:";
    public static final String NU_MERGEEE = " NU MERGEEE !";
    public static final String FIREBASE_FAIL1 = "FIREBASE FAIL:";
    public static final String DATELE_NU_SUNT_VALABILE = " Datele nu sunt valabile !";
    private  static FirebaseService firebaseService;

    //referinta catre primul nod = dao din sqlite
    private DatabaseReference database;


    private FirebaseService(){
                //nume tabela -> creeaza primul nod, asemanator cu o tabela
            database= FirebaseDatabase.getInstance().getReference("prieteni");
    }

    public static FirebaseService newInstance(){
        if(firebaseService==null){
            synchronized (FirebaseService.class){
                if(firebaseService==null){
                    firebaseService=new FirebaseService();

                }
            }
        }
        return firebaseService;
    }

//daca vreau sa adaug sub un nod principal un alt nod, un alt prieteni, trebuie sa adaug o cheie

    //METODA ISI DA SEAMA CAND ESTE VORBA DE UPDATE, CAND DE INSERT
    public void upsert(Prieten prieten) {
        if (prieten == null) {
            return;
        }
        if (prieten.getId() == null || prieten.getId().trim().isEmpty()) {
            String id = database.push().getKey();
            prieten.setId(id);
        }
        database.child(prieten.getId()).setValue(prieten);


    }


    public void delete(Prieten prieten){
        if(prieten==null || prieten.getId()==null || prieten.getId().trim().isEmpty()){
            return;
        }
        database.child(prieten.getId()).removeValue();
        database.child(prieten.getId()).removeEventListener(new ValueEventListener() {
            @Override
            //ne arata informatiile de sub prieteni
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i(FIREBASE_DO, MERGEEE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(FIREBASE_FAIL, NU_MERGEEE);

            }
        });
    }


    public void atasare(final Callback<List<Prieten>> callback){

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Prieten> prieteni=new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    Prieten prieten=data.getValue(Prieten.class);
                    if(prieten!=null){
                        prieteni.add(prieten);
                    }
                }
                callback.runResultOnUiThread(prieteni);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(FIREBASE_FAIL1, DATELE_NU_SUNT_VALABILE);

            }
        });
    }
}
