package com.cas.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cas.myapplication.controladores.DirectorControler;
import com.cas.myapplication.users.Director;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button registrar;
    private EditText nombre;
    private Button principal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DirectorControler direcControl = new DirectorControler();
        registrar = (Button) findViewById(R.id.button2);
        nombre = (EditText) findViewById(R.id.editText1);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registraDirector(direcControl);
            }
        });
        principal = (Button) findViewById(R.id.btn_temp);
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), DirectorVistaPrincipal.class);
                //startActivityForResult(intent,0);
            }
        });
    }


    public void registraDirector(DirectorControler directorControler){
        String nombre= this.nombre.getText().toString();
        Director nuevo = new Director();
        nuevo.setNombre(nombre);
        directorControler.registrarDirector(nuevo);

    }
}
