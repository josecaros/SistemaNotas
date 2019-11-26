package com.cas.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.cas.myapplication.resources.RegistrarDirector;
import com.cas.myapplication.resources.profesor.ProfesorMatricularAlumno;

public class profesorVistaPrincipal extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_vista_principal);
        toolbar= findViewById(R.id.myToolbarProfesor);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profesor_vista_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.matricularAñumno){
            Intent intent = new Intent (this, ProfesorMatricularAlumno.class);
            startActivityForResult(intent, 1);
            return true;
        }
        else if(item.getItemId()==R.id.salirProfesorVistaPrincipal){

            return true;
        }
        return (super.onOptionsItemSelected(item));

    }
}
