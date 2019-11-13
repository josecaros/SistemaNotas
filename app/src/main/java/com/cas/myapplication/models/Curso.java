package com.cas.myapplication.models;

public class Curso {
    String idCurso, nombre, descripcion;

    public Curso(String idCurso, String nombre, String descripcion) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Curso(){

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
}
