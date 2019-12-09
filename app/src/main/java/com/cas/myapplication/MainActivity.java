package com.cas.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cas.myapplication.resources.login.InicioSesionDialog;
import com.cas.myapplication.resources.login.InicioSesionDialogPadre;
import com.cas.myapplication.resources.profesor.ActualizarProfersorDialog;

public class MainActivity extends AppCompatActivity {
    private Button btnLoginDirector, btnLoginProfesor, btnLoginPadre;
    TextView reg_Director, forgot_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forgot_Pass=findViewById(R.id.forgotPassword);
        forgot_Pass.setMovementMethod(LinkMovementMethod.getInstance());

        btnLoginDirector = (Button) findViewById(R.id.botonLoginDirector);
        btnLoginDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioSesionDialog inicio  = new InicioSesionDialog(0);
                inicio.show(getSupportFragmentManager(),"InicioSesion");
            }
        });

        btnLoginProfesor = (Button) findViewById(R.id.botonLoginProfesor);
        btnLoginProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioSesionDialog inicio  = new InicioSesionDialog(1);
                inicio.show(getSupportFragmentManager(),"InicioSesion");
            }
        });
        btnLoginPadre = (Button) findViewById(R.id.botonLoginPadre);
        btnLoginPadre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioSesionDialogPadre inicio  = new InicioSesionDialogPadre();
                inicio.show(getSupportFragmentManager(),"InicioSesion");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==0){
            if(resultCode==100){

            }else if(resultCode==200){

            }
        }
        else if(requestCode==1){
            if(resultCode==100){
                Toast.makeText(this, "Director Agregado", (Toast.LENGTH_LONG)).show();
            }else if(resultCode==200){
                Toast.makeText(this, "Accion Cancelada", (Toast.LENGTH_LONG)).show();
            }
        }
        else if(requestCode==2){
            if(resultCode==100){

            }else if(resultCode==200){

            }
        }

    }
}
