package com.cas.myapplication.resources.alumno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.users.Alumno;

public class RegistrarAlumno extends AppCompatActivity {

    private Button btn_agregar, btn_cancelar;
    private EditText nombre, apellido, edad, idDNI, dniIDPadre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        agregarComponentes();
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlumnoControler controler= new AlumnoControler();
                Alumno alumno = new Alumno();
                alumno.setNombre(nombre.getText().toString());
                alumno.setApellido(apellido.getText().toString());
                alumno.setEdad(edad.getText().toString());
                alumno.setIdAlumno(idDNI.getText().toString());
                alumno.setPadreId(dniIDPadre.getText().toString());
                controler.registrarAlumno(alumno);

                Intent intent = new Intent();
                intent.putExtra("MESSAGE", "MESSAGE");
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
        btn_agregar = (Button) findViewById(R.id.btnRegAlum_agregar);
        btn_cancelar=(Button) findViewById(R.id.btnRegAlum_cancelar);
        nombre = (EditText) findViewById(R.id.txtEdiRegAlum_nombre);
        apellido = (EditText) findViewById(R.id.txtEdiRegAlum_apellido);
        idDNI = (EditText) findViewById(R.id.txtEdiRegAlum_dni);
        dniIDPadre = (EditText) findViewById(R.id.txtEdiRegAlum_dniPadre);
        edad = (EditText) findViewById(R.id.txtEdiRegAlum_edad);
    }
}
