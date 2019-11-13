package com.cas.myapplication.resources.cursos;

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
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.models.Curso;

public class ActualizarCursoDialog extends DialogFragment {
    Curso curso;
    CursoControler control;

    public ActualizarCursoDialog(Curso curs, CursoControler cursoControler){
        curso=curs;
        control=cursoControler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modifycursodialog,container,false);

        Button btnOk = view.findViewById(R.id.btnChangeCurs_ok);
        Button btnCancel = view.findViewById(R.id.btnChangeCurs_cancelar);

        final TextView nombre = view.findViewById((R.id.txtEdiChangeCurs_nombre));
        final TextView descri = view.findViewById((R.id.txtEdiChangeCurs_descri));

        nombre.setText(curso.getNombre());
        descri.setText(curso.getDescripcion());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Curso nuevo = new Curso();
                nuevo.setNombre(nombre.getText().toString());
                nuevo.setDescripcion(descri.getText().toString());
                control.actualizarCurso(curso.getIdCurso(),nuevo);
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
