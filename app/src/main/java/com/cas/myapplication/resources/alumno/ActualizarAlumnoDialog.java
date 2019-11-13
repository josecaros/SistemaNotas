package com.cas.myapplication.resources.alumno;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.users.Alumno;
import com.cas.myapplication.users.Profesor;

public class ActualizarAlumnoDialog extends DialogFragment {
    Alumno alumno;
    AlumnoControler control;

    public ActualizarAlumnoDialog(Alumno alumno, AlumnoControler alumnoControler){
        this.alumno=alumno;
        control=alumnoControler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modifyalumnodialog,container,false);
        Log.i("ActualizarDialog", "hasta aqui");
        Button btnOk = view.findViewById(R.id.btnChangeAlum_ok);
        Button btnCancel = view.findViewById(R.id.btnChangeAlum_cancelar);

        final TextView nombre = view.findViewById((R.id.txtEdiChangeAlum_nombre));
        final TextView apellido = view.findViewById((R.id.txtEdiChangeAlum_apellido));
        final TextView dniPadre = view.findViewById((R.id.txtEdiChangeAlum_dniPadre));
        final TextView dni = view.findViewById((R.id.txtEdiChangeAlum_dni));
        final TextView edad = view.findViewById(R.id.txtEdiChangeAl)
        nombre.setText(alumno.getNombre());
        apellido.setText(alumno.getApellido());
        dniPadre.setText(alumno.getPadreId());
        if(!alumno.getIdAlumno().equals("")){
            dni.setText(alumno.getIdAlumno());
            dni.setEnabled(false);
        }


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno nuevo = new Alumno();
                nuevo.setNombre(nombre.getText().toString());
                nuevo.setApellido(apellido.getText().toString());
                nuevo.setPadreId(dniPadre.getText().toString());
                nuevo.setIdAlumno(dni.getText().toString());

                control.actualizarAlumno(alumno.getIdAlumno(),nuevo);
                getDialog().dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;
    }
}
