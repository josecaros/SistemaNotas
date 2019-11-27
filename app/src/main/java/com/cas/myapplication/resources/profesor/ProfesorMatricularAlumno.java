package com.cas.myapplication.resources.profesor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.controladores.MatriculaControler;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Matricula;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class ProfesorMatricularAlumno extends AppCompatActivity implements ListenerOfDataBase {
    SearchableSpinner alumSpinner, cursSpinner;
    ListenerOfDataBase interConexion;
    List<Alumno> alumnos = new ArrayList<>();
    List<Curso> cursos = new ArrayList<>();
    Button matricular;
    String proId= "-LtNU_7gPPWbQxnm0Zup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_matricular_alumno);

        alumSpinner= (SearchableSpinner) findViewById(R.id.spinnerSearchAlumno);
        cursSpinner= (SearchableSpinner) findViewById(R.id.spinnerSearchCurso);
        CursoControler controlCurso = new CursoControler();
        final AlumnoControler controlAlumno = new AlumnoControler();
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
        matricular= (Button) findViewById(R.id.btn_matricularAlumno);
        matricular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matricula matri = new Matricula();
                matri.setIdProfesor(proId);
                matri.setIdAlumno(alumnos.get(alumSpinner.getSelectedItemPosition()).getIdAlumno());
                matri.setIdCurso(cursos.get(cursSpinner.getSelectedItemPosition()).getIdCurso());
                matri.setNombreAlumno(alumnos.get(alumSpinner.getSelectedItemPosition()).getNombre()+
                        " "+alumnos.get(alumSpinner.getSelectedItemPosition()).getApellido());
                MatriculaControler control = new MatriculaControler();
                control.registrarMatricula(matri);
                finish();
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
