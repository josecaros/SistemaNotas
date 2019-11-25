package com.cas.myapplication.models;

public class Padre {
    String idPadre, nombre, apellido, telefono;

    public Padre(String idPadre, String nombre, String apellido, String telefono, String direccion) {
        this.idPadre = idPadre;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    public Padre(){}

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
