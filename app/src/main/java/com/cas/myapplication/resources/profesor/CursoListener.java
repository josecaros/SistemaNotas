package com.cas.myapplication.resources.profesor;

import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Matricula;

import java.util.List;

public interface CursoListener {
    public void loadSucessfull(List<Curso> cursoList);
    public void loadSucessfullMatricula(List<Matricula> matriculaList);
}
