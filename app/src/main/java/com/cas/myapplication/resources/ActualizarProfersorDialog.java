package com.cas.myapplication.resources;

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
import com.cas.myapplication.users.Profesor;

public class ActualizarProfersorDialog extends DialogFragment {
    Profesor profesor;

    public ActualizarProfersorDialog(Profesor prof){
        profesor=prof;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modifyprofesordialog,container,false);

        Button btnOk = view.findViewById(R.id.btnChangeProf_ok);
        Button btnCancel = view.findViewById(R.id.btnChangeProf_cancelar);

        TextView nombre = view.findViewById((R.id.txtEdiChangeProf_nombre));
        TextView apellido = view.findViewById((R.id.txtEdiChangeProf_nombre));
        TextView correo = view.findViewById((R.id.txtEdiChangeProf_nombre));
        TextView telefono = view.findViewById((R.id.txtEdiChangeProf_nombre));
        TextView direccion = view.findViewById((R.id.txtEdiChangeProf_nombre));
        TextView dni = view.findViewById((R.id.txtEdiChangeProf_dni));


        return view;
    }

}
