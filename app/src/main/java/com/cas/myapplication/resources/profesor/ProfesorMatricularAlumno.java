package com.cas.myapplication.resources.profesor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Curso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class ProfesorMatricularAlumno extends AppCompatActivity implements ListenerOfDataBase {
    SearchableSpinner alumSpinner, cursSpinner;
    ListenerOfDataBase interConexion;
    List<Alumno> alumnos = new ArrayList<>();
    List<Curso> cursos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_matricular_alumno);

        alumSpinner= (SearchableSpinner) findViewById(R.id.spinnerSearchAlumno);
        cursSpinner= (SearchableSpinner) findViewById(R.id.spinnerSearchCurso);
        CursoControler controlCurso = new CursoControler();
        AlumnoControler controlAlumno = new AlumnoControler();
        interConexion = this;

        controlAlumno.getAlumnos().child("Alumno").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Alumno> cur= new ArrayList<>();
                for (DataSnapshot obs: dataSnapshot.getChildren()){
                    cur.add(obs.getValue(Alumno.class));
                }
                interConexion.onFirebaseLoadCorrectAlumno(cur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        controlCurso.getCursos().child("Curso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Curso> cur= new ArrayList<>();
                for (DataSnapshot obs: dataSnapshot.getChildren()){
                    cur.add(obs.getValue(Curso.class));
                }
                interConexion.onFirebaseLoadCorrectCurso(cur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onFirebaseLoadCorrectCurso(List<Curso> cursoList) {
        cursos=cursoList;
        List<String> nombres = new ArrayList<>();
        for(Curso cur:cursos){
            nombres.add(cur.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nombres);
        cursSpinner.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadCorrectAlumno(List<Alumno> alumnoList) {
        alumnos=alumnoList;
        List<String> nombres = new ArrayList<>();
        for(Alumno alumno:alumnos){
            nombres.add(alumno.getIdAlumno());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nombres);
        alumSpinner.setAdapter(adapter);
    }
}
