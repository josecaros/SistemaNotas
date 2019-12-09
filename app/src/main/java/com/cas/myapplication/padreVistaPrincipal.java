package com.cas.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.resources.alumno.AdaptadorListaAlumnos;
import com.cas.myapplication.resources.alumno.AdaptadorListaAlumnosPadre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class padreVistaPrincipal extends AppCompatActivity  {
    AdaptadorListaAlumnos alumnoAdapter;
    List<Alumno> listaAlumno;
    ListView listView;
    private AlumnoControler alumnoControler = new AlumnoControler();
    String idKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padre_vista_principal);
        idKey = getIntent().getStringExtra("ID");
        listView  = (ListView) findViewById(R.id.list_view_hijos);

        listaAlumno = alumnoControler.getAll();
        listarDatos();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent (padreVistaPrincipal.this, padreVistaHijo.class);
                intent.putExtra("idAlumno",listaAlumno.get(position).getIdAlumno());
                intent.putExtra("nombreAlumno",listaAlumno.get(position).getNombre()+" "+listaAlumno.get(position).getApellido());
                startActivityForResult(intent, 3);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==3){
            if(resultCode==100){

            }else if(resultCode==200){

            }
        }
    }

    private void listarDatos(){
        alumnoControler.getAlumnos().child("Alumno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaAlumno.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Alumno aux = objtSnap.getValue(Alumno.class);
                    if(aux.getPadreId().equals(idKey)) {
                        listaAlumno.add(aux);
                        alumnoAdapter = new AdaptadorListaAlumnos(padreVistaPrincipal.this, R.id.list_view_hijos, listaAlumno);
                        listView.setAdapter(alumnoAdapter);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
