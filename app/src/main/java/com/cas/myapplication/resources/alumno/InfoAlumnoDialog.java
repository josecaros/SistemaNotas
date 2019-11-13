package com.cas.myapplication.resources.alumno;

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
import com.cas.myapplication.users.Alumno;
import com.cas.myapplication.users.Profesor;

public class InfoAlumnoDialog extends DialogFragment {
    Alumno alumno;

    public InfoAlumnoDialog(Alumno nuevo){
        alumno=nuevo;
        Bundle args = new Bundle();
        args.putString("title", "Información");
        setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoalumnodialog, container, false);
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
        TextView nombre = view.findViewById(R.id.txtViewInfoDialogNombresAlum);
        nombre.setText(alumno.getNombre()+" "+ alumno.getApellido());
        TextView dni = view.findViewById(R.id.txtViewInfoDialogDNIAlum);
        dni.setText(alumno.getIdAlumno());
        TextView dniPadre = view.findViewById(R.id.txtViewInfoDialogDNIPadreAlum);
        dniPadre.setText(alumno.getPadreId());

        return view;
    }
}
