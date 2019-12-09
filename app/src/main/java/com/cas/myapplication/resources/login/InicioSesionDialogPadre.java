package com.cas.myapplication.resources.login;

import android.content.Intent;
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
import com.cas.myapplication.controladores.PadreControler;
import com.cas.myapplication.directorVistaPrincipal;
import com.cas.myapplication.models.Director;
import com.cas.myapplication.models.Padre;
import com.cas.myapplication.models.Profesor;
import com.cas.myapplication.padreVistaPrincipal;
import com.cas.myapplication.profesorVistaPrincipal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class InicioSesionDialogPadre extends DialogFragment {
    boolean estado;
    String KeyID;
    public InicioSesionDialogPadre(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_2,container,false);

        final EditText user= view.findViewById(R.id.loginUserLog2);
        final Button ok = view.findViewById(R.id.login_btn_ok2);
        final Button cancel = view.findViewById(R.id.login_btn_cancel2);
        estado=false;
        final PadreControler control = new PadreControler();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = user.getText().toString();
                control.getPadres().child("Padre").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Padre aux = data.getValue(Padre.class);
                            if (aux.getIdPadre().equals(id)) {
                                estado = true;
                                KeyID = aux.getIdPadre();
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                if(estado){
                    Intent intent = new Intent(view.getContext(), padreVistaPrincipal.class);
                    intent.putExtra("ID",KeyID);
                    startActivityForResult(intent,0);
                    getDialog().dismiss();
                }

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
