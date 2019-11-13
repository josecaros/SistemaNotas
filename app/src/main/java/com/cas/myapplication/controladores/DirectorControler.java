package com.cas.myapplication.controladores;

import androidx.annotation.NonNull;

import com.cas.myapplication.models.Director;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DirectorControler {
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference directores;

    public DirectorControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        directores = firebaseDatabase.getReference();
    }

    public int registrarDirector(Director director){
        String id = directores.push().getKey();
        director.setIdDirector(id);
        directores.child("Director").child(id).setValue(director);
        return 100;
    }

    public ArrayList<Director> getAll(){
        final List<Director> lista = new ArrayList<>();
        directores.child("Director").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSS: dataSnapshot.getChildren()){
                    Director director = objSS.getValue(Director.class);
                    lista.add(director);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return new ArrayList(lista);
    }

    public void actualizarDirector(String id, Director director){
        Director modificated = new Director();
        modificated.setIdDirector(id);
        modificated.setNombre(director.getNombre());
        modificated.setApellido(director.getApellido());
        modificated.setCorreo(director.getCorreo());
        modificated.setDireccion(director.getDireccion());
        modificated.setPassword(director.getPassword());
        modificated.setTelefono(director.getTelefono());
        directores.child("Director").child(modificated.getIdDirector()).setValue(modificated);
    }

    public void eliminarDirector(String id){
        directores.child("Director").child(id).removeValue();
    }


}
