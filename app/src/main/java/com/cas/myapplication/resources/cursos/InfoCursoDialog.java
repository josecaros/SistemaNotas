package com.cas.myapplication.resources.cursos;

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
import com.cas.myapplication.models.Curso;

public class InfoCursoDialog extends DialogFragment {
    Curso curso;

    public InfoCursoDialog(Curso curs){
        curso=curs;
        Bundle args = new Bundle();
        args.putString("title", "Información");
        setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infocursodialog, container, false);
        Button btn_ok = (Button)view.findViewById(R.id.btnViewInfoDialogCurs_ok);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("", "Boton Aceptar");
                dismiss();
            }
        });
        TextView nombre = view.findViewById(R.id.txtViewInfoDialogNombresCurs);
        nombre.setText(curso.getNombre());
        TextView desc = view.findViewById(R.id.txtViewInfoDialogDescrCurs);
        desc.setText(curso.getDescripcion());
        return view;
    }
}
