package com.cas.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class padreVistaHijo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinnerMes, spinnerCurso;
    ArrayAdapter<CharSequence> adapterM, adapterC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padre_vista_hijo);

        spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
        spinnerCurso = (Spinner) findViewById(R.id.spinnerCurso);

        adapterM = ArrayAdapter.createFromResource(this,R.array.spinnerSem,android.R.layout.simple_spinner_item);
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(adapterM);
        spinnerMes.setOnItemSelectedListener(this);

        adapterC = ArrayAdapter.createFromResource(this,R.array.spinnerSem,android.R.layout.simple_spinner_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurso.setAdapter(adapterC);
        spinnerCurso.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
