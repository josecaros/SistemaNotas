package com.cas.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.controladores.MatriculaControler;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Matricula;
import com.cas.myapplication.resources.alumno.AdaptadorListaAlumnos;
import com.cas.myapplication.resources.alumno.AdaptadorListaNotasAlumnos;
import com.cas.myapplication.resources.profesor.ActualizarRegistroNota;
import com.cas.myapplication.resources.profesor.CursoListener;
import com.cas.myapplication.resources.profesor.ProfesorMatricularAlumno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class profesorVistaPrincipal extends AppCompatActivity implements CursoListener {
    private Toolbar toolbar;
    private SearchableSpinner cursosSpinner;
    ListView lista;
    CursoListener listenerC;
    List<Curso> cursos;
    List<Alumno> alumnos;
    List<Matricula> matricula = new ArrayList<>();
    boolean first=true;
    AlumnoControler alumnoControler =new AlumnoControler();
    AdaptadorListaAlumnos adapter;
    String idKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_vista_principal);
        toolbar= findViewById(R.id.myToolbarProfesor);
        setSupportActionBar(toolbar);
        idKey = getIntent().getStringExtra("ID");

        listenerC=this;
        cursosSpinner = (SearchableSpinner) findViewById(R.id.spinnerForCursosProfesor);
        lista = (ListView) findViewById(R.id.listaAlumnosCursosMatriculaldos);
        final CursoControler controlC = new CursoControler();
        controlC.getCursos().child("Curso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Curso> cur= new ArrayList<>();
                for (DataSnapshot obs: dataSnapshot.getChildren()){
                    Curso aux =obs.getValue(Curso.class);
                    if(aux.getIdProfesor().equals(idKey)){
                        cur.add(aux);
                    }
                }
                listenerC.loadSucessfull(cur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cursosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!first){
                    final Curso curso = cursos.get(position);
                    final List<Matricula> matriculados= new ArrayList<>();
                    MatriculaControler controlM = new MatriculaControler();
                    controlM.getMatriculas().child("Matricula").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            matricula.clear();
                            for(DataSnapshot objss : dataSnapshot.getChildren()){
                                Matricula matr = objss.getValue(Matricula.class);
                                if(matr.getIdCurso().equals(curso.getIdCurso())){
                                    matriculados.add(matr);
                                    Log.i("matriculas",matr.getIdCurso());
                                }
                            }
                            listenerC.loadSucessfullMatricula(matriculados);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                    first=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActualizarRegistroNota nota = new ActualizarRegistroNota((Matricula) lista.getItemAtPosition(position));
                nota.show(getSupportFragmentManager(),"Actualizar Nota");
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profesor_vista_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.matricularAñumno){
            Intent intent = new Intent (this, ProfesorMatricularAlumno.class);
            intent.putExtra("ID",idKey);
            startActivityForResult(intent, 1);
            return true;
        }
        else if(item.getItemId()==R.id.salirProfesorVistaPrincipal){
            finish();
            return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void loadSucessfull(List<Curso> cursoList) {
        cursos=cursoList;
        List<String> nombres = new ArrayList<>();
        for(Curso cur:cursos){
            nombres.add(cur.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nombres);
        cursosSpinner.setAdapter(adapter);
    }

    @Override
    public void loadSucessfullMatricula(List<Matricula> matriculaList) {
        matricula=matriculaList;
        AdaptadorListaNotasAlumnos adapter = new AdaptadorListaNotasAlumnos(this,R.layout.row_list_alumn_profesor,matricula);
        lista.setAdapter(adapter);
    }
}
