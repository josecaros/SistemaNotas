package com.cas.myapplication.resources;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cas.myapplication.R;
import com.cas.myapplication.users.Profesor;

import org.w3c.dom.Text;

public class InfoProfesorDialog extends DialogFragment {
    Profesor profesor;

    public InfoProfesorDialog(Profesor nuevo){
        profesor=nuevo;
        Bundle args = new Bundle();
        args.putString("title", "Información");
        setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoprofesordialog, container, false);
        Button btn_ok = (Button)view.findViewById(R.id.btnViewInfoDialogProf_ok);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("", "Boton Aceptar");
                dismiss();
            }
        });
        TextView nombre = view.findViewById(R.id.txtViewInfoDialogNombres);
        nombre.setText(profesor.getNombre()+" "+ profesor.getApellidos());
        TextView correo = view.findViewById(R.id.txtViewInfoDialogCorreo);
        correo.setText(profesor.getCorreo());
        TextView dni = view.findViewById(R.id.txtViewInfoDialogDNI);
        dni.setText(profesor.getDni());
        TextView direccion = view.findViewById(R.id.txtViewInfoDialogDireccion);
        direccion.setText(profesor.getDireccion());
        TextView telefono = view.findViewById(R.id.txtViewInfoDialogTelefono);
        telefono.setText(profesor.getTelefono());
        TextView password = view.findViewById(R.id.txtViewInfoDialogPassword);
        password.setText(profesor.getPassword());

        return view;
    }
}
