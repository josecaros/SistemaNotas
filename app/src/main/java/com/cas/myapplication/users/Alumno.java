package com.cas.myapplication.users;

public class Alumno {
    String nombre, apellido, idAlumno, edad, padreId;

    public Alumno(String nombre, String apellido, String idAlumno, String edad, String padreId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idAlumno = idAlumno;
        this.edad = edad;
        this.padreId = padreId;
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

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPadreId() {
        return padreId;
    }

    public void setPadreId(String padreId) {
        this.padreId = padreId;
    }
}
