package com.cas.myapplication.resources.profesor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.MatriculaControler;
import com.cas.myapplication.models.Matricula;

public class ActualizarRegistroNota extends DialogFragment {
    Matricula matricula;
    public ActualizarRegistroNota(Matricula id){
        this.matricula=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.agregar_nota_profesor,container,false);

        final EditText nota = view.findViewById(R.id.actualizarNotaProfesor);
        final Button ok = view.findViewById(R.id.actualizarNotaButtonOk);
        final Button cancel = view.findViewById(R.id.actualizarNotaButtonCancel);

        final MatriculaControler control = new MatriculaControler();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matricula.setIdNota(nota.getText().toString());
                control.actualizarMatricula(matricula.getIdMatricula(),matricula);
                getDialog().dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
