package com.cas.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cas.myapplication.controladores.MatriculaControler;
import com.cas.myapplication.models.Matricula;
import com.cas.myapplication.models.Profesor;
import com.cas.myapplication.resources.alumno.AdaptadorListaNotasAlumnos;
import com.cas.myapplication.resources.profesor.AdaptadorListaProfesores;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class padreVistaHijo extends AppCompatActivity {
    ListView lista;
    List<Matricula> matriculas;
    AdaptadorListaNotasAlumnos adapter;
    MatriculaControler control = new MatriculaControler();
    TextView nombre;
    String idKey;
    String nombreAlumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padre_vista_hijo);
        idKey= getIntent().getStringExtra("idAlumno");
        nombreAlumno= getIntent().getStringExtra("nombreAlumno");
        lista = findViewById(R.id.padreLista);
        matriculas=new ArrayList<>();
        nombre= findViewById(R.id.nombreAlumnoVistaPadre);
        nombre.setText(nombreAlumno);
        listarDatos();
    }

    private void listarDatos(){
        control.getMatriculas().child("Matricula").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                matriculas.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Matricula aux = objtSnap.getValue(Matricula.class);
                    if(aux.getIdAlumno().equals(idKey)){
                        matriculas.add(aux);
                        adapter = new AdaptadorListaNotasAlumnos(padreVistaHijo.this, R.layout.row_list_alumn_profesor, matriculas);
                        lista.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
