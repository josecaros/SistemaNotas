package com.cas.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.resources.AdaptadorListaProfesores;
import com.cas.myapplication.resources.RegistrarProfesor;
import com.cas.myapplication.users.Director;
import com.cas.myapplication.users.Profesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorProfesorFragment extends Fragment {
    ArrayList<Profesor> profesores = new ArrayList<>();
    ArrayAdapter<Profesor> profesor;
    AdaptadorListaProfesores adapter;
    ListView lista;
    Button btn_añadir;
    private ProfesorControler profesorControler = new ProfesorControler();

    public DirectorProfesorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_director_profesor, container, false);
        Log.i("tab","PRofesorFragment");
        lista = (ListView) vista.findViewById(R.id.list_view_profesores);
        btn_añadir = (Button) vista.findViewById(R.id.btn_regNuevoProfesor);
        btn_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , RegistrarProfesor.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
                Log.i("MainActivity", "Abriendo otro activity");
            }
        });

        profesores = profesorControler.getAll();
        listarDatos();

        return vista;
    }

    private void listarDatos(){
        profesorControler.getProfesores().child("Profesor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profesores.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Profesor aux = objtSnap.getValue(Profesor.class);
                    profesores.add(aux);
                    adapter = new AdaptadorListaProfesores(getActivity().getApplicationContext(), R.id.list_view_profesores, profesores);
                    lista.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 100) {
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getBaseContext(), "Añadido Correctamente", (Toast.LENGTH_LONG)).show();
            } else if (resultCode == 200) {
                Toast.makeText(getActivity().getBaseContext(), "Se cancelo la acción", (Toast.LENGTH_LONG)).show();
            }

        }
    }
}
