package com.cas.myapplication.controladores;

import androidx.annotation.NonNull;

import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Profesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CursoControler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference cursos;

    public CursoControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        cursos = firebaseDatabase.getReference();
    }

    public int registrarCurso(Curso curso){
        String id = cursos.push().getKey();
        curso.setIdCurso(id);
        cursos.child("Curso").child(id).setValue(curso);
        return 100;
    }

    public ArrayList<Curso> getAll(){
        final ArrayList<Curso> lista = new ArrayList<>();
        cursos.child("Curso").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSS: dataSnapshot.getChildren()){
                    Curso curso = objSS.getValue(Curso.class);
                    lista.add(curso);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;
    }

    public void actualizarCurso(String id, Curso curso){
        Curso modificated = new Curso();
        modificated.setIdCurso(id);
        modificated.setDescripcion(curso.getDescripcion());
        modificated.setNombre(curso.getNombre());
        cursos.child("Curso").child(modificated.getIdCurso()).setValue(modificated);
    }

    public void eliminarCurso(String id){
        cursos.child("Curso").child(id).removeValue();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getCursos() {
        return cursos;
    }
}
