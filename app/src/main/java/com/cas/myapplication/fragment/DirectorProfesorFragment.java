package com.cas.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cas.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorProfesorFragment extends Fragment {


    public DirectorProfesorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_director_profesor, container, false);
    }

}
