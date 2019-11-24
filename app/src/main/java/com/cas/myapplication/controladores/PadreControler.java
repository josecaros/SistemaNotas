package com.cas.myapplication.controladores;

import androidx.annotation.NonNull;

import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Padre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PadreControler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference padres;

    public PadreControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        padres = firebaseDatabase.getReference();
    }

    public int registrarPadre(Padre padre){
        padres.child("Padre").child(padre.getIdPadre()).setValue(padre);
        return 100;
    }

    public ArrayList<Padre> getAll(){
        final ArrayList<Padre> lista = new ArrayList<>();
        padres.child("Padre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSS: dataSnapshot.getChildren()){
                    Padre padre = objSS.getValue(Padre.class);
                    lista.add(padre);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;
    }

    public void actualizarPadre(String id, Padre padre){
        Padre modificated = new Padre();
        modificated.setIdPadre(id);
        modificated.setNombre(padre.getNombre());
        modificated.setApellido(padre.getApellido());
        modificated.setTelefono(padre.getTelefono());
        padres.child("Padre").child(modificated.getIdPadre()).setValue(modificated);
    }

    public void eliminarAlumno(String id){
        padres.child("Padre").child(id).removeValue();
    }
}
