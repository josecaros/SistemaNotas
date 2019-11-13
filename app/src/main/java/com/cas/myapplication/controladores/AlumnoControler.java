package com.cas.myapplication.controladores;

import androidx.annotation.NonNull;

import com.cas.myapplication.models.Alumno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AlumnoControler {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference alumnos;

    public AlumnoControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        alumnos = firebaseDatabase.getReference();
    }

    public int registrarAlumno(Alumno alumno){
        alumnos.child("Alumno").child(alumno.getIdAlumno()).setValue(alumno);
        return 100;
    }

    public ArrayList<Alumno> getAll(){
        final ArrayList<Alumno> lista = new ArrayList<>();
        alumnos.child("Alumno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSS: dataSnapshot.getChildren()){
                    Alumno alumno = objSS.getValue(Alumno.class);
                    lista.add(alumno);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;
    }

    public void actualizarAlumno(String id, Alumno alumno){
        Alumno modificated = new Alumno();
        modificated.setIdAlumno(id);
        modificated.setNombre(alumno.getNombre());
        modificated.setApellido(alumno.getApellido());
        modificated.setEdad(alumno.getEdad());
        modificated.setPadreId(alumno.getPadreId());
        alumnos.child("Alumno").child(modificated.getIdAlumno()).setValue(modificated);
    }

    public void eliminarAlumno(String id){
        alumnos.child("Alumno").child(id).removeValue();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getAlumnos() {
        return alumnos;
    }
}
