package com.cas.myapplication.controladores;
import com.cas.myapplication.models.Matricula;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MatriculaControler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference matriculas;

    public MatriculaControler(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        matriculas = firebaseDatabase.getReference();
    }

    public int registrarMatricula(Matricula matricula){
        String id = matriculas.push().getKey();
        matricula.setIdMatricula(id);
        matriculas.child("Matricula").child(id).setValue(matricula);
        return 100;
    }

    public void actualizarPadre(String id, Matricula matricula){
        Matricula modificated = new Matricula();
        modificated.setIdMatricula(id);
        modificated.setIdCurso(matricula.getIdCurso());
        modificated.setIdAlumno(matricula.getIdAlumno());
        modificated.setIdProfesor(matricula.getIdProfesor());
        modificated.setIdNota(matricula.getIdNota());
        modificated.setNombreAlumno(matricula.getNombreAlumno());
        matriculas.child("Matricula").child(modificated.getIdMatricula()).setValue(modificated);
    }

    public void eliminarPadre(String id){
        matriculas.child("Matricula").child(id).removeValue();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }
    public DatabaseReference getMatriculas() {
        return matriculas;
    }
}
