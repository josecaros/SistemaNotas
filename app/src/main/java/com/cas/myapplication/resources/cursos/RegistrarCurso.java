package com.cas.myapplication.resources.cursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Profesor;

public class RegistrarCurso extends AppCompatActivity {
    private Button btn_agregar, btn_cancelar;
    private EditText nombre, descri,profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_curso);
        agregarComponentes();
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CursoControler controler = new CursoControler();
                Curso nuevoCurso = new Curso();
                nuevoCurso.setNombre(nombre.getText().toString());
                nuevoCurso.setDescripcion(descri.getText().toString());
                nuevoCurso.setIdProfesor(profesor.getText().toString());
                controler.registrarCurso(nuevoCurso);
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","MESSAGE");
                setResult(100,intent);
                finish();
            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(200);
                finish();
            }
        });
    }

    private void agregarComponentes(){
        btn_agregar = (Button) findViewById(R.id.btnRegCurs_agregar);
        btn_cancelar=(Button) findViewById(R.id.btnRegCurs_cancelar);
        nombre = (EditText) findViewById(R.id.txtEdiRegCurs_nombre);
        descri = (EditText) findViewById(R.id.txtEdiRegCurso_Descri);
        profesor= (EditText) findViewById(R.id.txtEdiRegCurso_Profesor);
    }
}
