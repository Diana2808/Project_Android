package com.example.project.fragmente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;


public class ColectieFragment extends Fragment {





    public ColectieFragment() {

    }


    public static ColectieFragment newInstance(String param1, String param2) {
        ColectieFragment fragment = new ColectieFragment();
        Bundle bundle = new Bundle();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colectie, container, false);
    }
}