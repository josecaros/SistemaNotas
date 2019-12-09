package com.cas.myapplication.models;

public class Curso {
    String idCurso, nombre, descripcion, idProfesor;

    public Curso(String idCurso, String nombre,String profesor ,String descripcion) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProfesor= profesor;
    }
    public Curso(){

    }

    public void copyFromCurso(Curso cur){
        idCurso= cur.getIdCurso();
        nombre=cur.getNombre();
        descripcion=cur.getDescripcion();
        idProfesor=cur.getIdProfesor();
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }
}
