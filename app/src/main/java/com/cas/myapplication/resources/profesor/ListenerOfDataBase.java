package com.cas.myapplication.resources.profesor;

import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Curso;

import java.util.List;

public interface ListenerOfDataBase {
    void onFirebaseLoadCorrectCurso(List<Curso> cursoList);
    void onFirebaseLoadCorrectAlumno(List<Alumno> alumnoList);
}
