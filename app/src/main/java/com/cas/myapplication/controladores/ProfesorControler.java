package com.cas.myapplication.controladores;

import androidx.annotation.NonNull;

import com.cas.myapplication.models.Profesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfesorControler {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference profesores;

    public ProfesorControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        profesores = firebaseDatabase.getReference();
    }

    public int registrarProfesor(Profesor profesor){
        String id = profesores.push().getKey();
        profesor.setIdProfesor(id);
        profesores.child("Profesor").child(id).setValue(profesor);
        return 100;
    }

    public ArrayList<Profesor> getAll(){
        final ArrayList<Profesor> lista = new ArrayList<>();
        profesores.child("Profesor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSS: dataSnapshot.getChildren()){
                    Profesor profesor = objSS.getValue(Profesor.class);
                    lista.add(profesor);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;
    }

    public void actualizarProfesor(String id, Profesor profe){
        Profesor modificated = new Profesor();
        modificated.setIdProfesor(id);
        modificated.setNombre(profe.getNombre());
        modificated.setApellidos(profe.getApellidos());
        modificated.setCorreo(profe.getCorreo());
        modificated.setDireccion(profe.getDireccion());
        modificated.setPassword(profe.getPassword());
        modificated.setTelefono(profe.getTelefono());
        modificated.setDni(profe.getDni());
        profesores.child("Profesor").child(modificated.getIdProfesor()).setValue(modificated);
    }

    public void eliminarProfesor(String id){
        profesores.child("Profesor").child(id).removeValue();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getProfesores() {
        return profesores;
    }
}
