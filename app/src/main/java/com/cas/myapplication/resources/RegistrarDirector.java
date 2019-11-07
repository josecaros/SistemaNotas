package com.cas.myapplication.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.DirectorControler;
import com.cas.myapplication.users.Director;

public class RegistrarDirector extends AppCompatActivity {
    private Button registrar, cancelar;
    private EditText nombre, apellido, password, correo, direccion, telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_director);
        agregarComponentes();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DirectorControler control = new DirectorControler();
                Director director = new Director();
                director.setNombre(nombre.getText().toString());
                director.setApellido(apellido.getText().toString());
                director.setPassword(password.getText().toString());
                director.setCorreo(correo.getText().toString());
                director.setDireccion(direccion.getText().toString());
                director.setTelefono(telefono.getText().toString());
                control.registrarDirector(director);
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","MESSAGE");
                setResult(100,intent);
                finish();
            }
        });
    }

    private void agregarComponentes() {
        nombre = findViewById(R.id.txtEdiRegDir_nombre);
        apellido = findViewById(R.id.txtEdiRegDir_apellido);
        password = findViewById(R.id.txtEdiRegDir_password);
        correo = findViewById(R.id.txtEdiRegDir_correo);
        direccion = findViewById(R.id.txtEdiRegDir_direccion);
        telefono = findViewById(R.id.txtEdiRegDir_telefono);

        registrar = findViewById(R.id.btnRegDir_registrar);
        cancelar = findViewById(R.id.btnRegDir_cancelar);
    }
}
