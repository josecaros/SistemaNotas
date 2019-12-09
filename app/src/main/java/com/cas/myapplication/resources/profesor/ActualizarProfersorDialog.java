package com.cas.myapplication.resources.profesor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.models.Profesor;

public class ActualizarProfersorDialog extends DialogFragment {
    Profesor profesor;
    ProfesorControler control;

    public ActualizarProfersorDialog(Profesor prof, ProfesorControler profesorControler){
        profesor=prof;
        control=profesorControler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modifyprofesordialog,container,false);

        Button btnOk = view.findViewById(R.id.btnChangeProf_ok);
        Button btnCancel = view.findViewById(R.id.btnChangeProf_cancelar);

        final TextView nombre = view.findViewById((R.id.txtEdiChangeProf_nombre));
        final TextView apellido = view.findViewById((R.id.txtEdiChangeProf_apellido));
        final TextView correo = view.findViewById((R.id.txtEdiChangeProf_correo));
        final TextView telefono = view.findViewById((R.id.txtEdiChangeProf_telefono));
        final TextView direccion = view.findViewById((R.id.txtEdiChangeProf_direccion));
        final TextView dni = view.findViewById((R.id.txtEdiChangeProf_dni));
        final TextView password = view.findViewById((R.id.txtEdiChangeProf_password));
        password.setText(profesor.getPassword());
        nombre.setText(profesor.getNombre());
        apellido.setText(profesor.getApellidos());
        correo.setText(profesor.getCorreo());
        telefono.setText(profesor.getTelefono());
        direccion.setText(profesor.getDireccion());
        if(!profesor.getDni().equals("")){
            dni.setText(profesor.getDni());
            dni.setEnabled(false);
        }


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profesor nuevo = new Profesor();
                nuevo.setNombre(nombre.getText().toString());
                nuevo.setApellidos(apellido.getText().toString());
                nuevo.setCorreo(correo.getText().toString());
                nuevo.setTelefono(telefono.getText().toString());
                nuevo.setDireccion(direccion.getText().toString());
                nuevo.setDni(dni.getText().toString());
                nuevo.setPassword(password.getText().toString());
                control.actualizarProfesor(profesor.getDni(),nuevo);
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
