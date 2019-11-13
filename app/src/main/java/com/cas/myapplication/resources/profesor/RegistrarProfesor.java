package com.cas.myapplication.resources.profesor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.users.Profesor;

public class RegistrarProfesor extends AppCompatActivity {
    private Button btn_agregar, btn_cancelar;
    private EditText nombre, apellido, telefono, correo, direccion, password, dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_profesor);
        agregarComponentes();
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfesorControler controler = new ProfesorControler();
                Profesor nuevoProfesor = new Profesor();
                nuevoProfesor.setDni(dni.getText().toString());
                nuevoProfesor.setNombre(nombre.getText().toString());
                nuevoProfesor.setApellidos(apellido.getText().toString());
                nuevoProfesor.setTelefono(telefono.getText().toString());
                nuevoProfesor.setCorreo(correo.getText().toString());
                nuevoProfesor.setDireccion(direccion.getText().toString());
                nuevoProfesor.setPassword(password.getText().toString());
                controler.registrarProfesor(nuevoProfesor);
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
        btn_agregar = (Button) findViewById(R.id.btnRegProf_agregar);
        btn_cancelar=(Button) findViewById(R.id.btnRegProf_cancelar);
        nombre = (EditText) findViewById(R.id.txtEdiRegProf_nombre);
        apellido = (EditText) findViewById(R.id.txtEdiRegProf_apellido);
        telefono = (EditText) findViewById(R.id.txtEdiRegProf_telefono);
        correo = (EditText) findViewById(R.id.txtEdiRegProf_correo);
        direccion = (EditText) findViewById(R.id.txtEdiRegProf_direccion);
        dni = (EditText) findViewById(R.id.txtEdiRegProf_dni);
        password = (EditText) findViewById(R.id.txtEdiRegProf_password);
    }

}
