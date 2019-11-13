package com.cas.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cas.myapplication.resources.RegistrarDirector;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin, aux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, directorVistaPrincipal.class);
                startActivityForResult(intent,0);
            }
        });

        aux = (Button) findViewById(R.id.botonTemporal);
        aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), RegistrarDirector.class);
                startActivityForResult(intent, 1);
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

    }
}
