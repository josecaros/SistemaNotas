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

import com.cas.myapplication.MainActivity;
import com.cas.myapplication.R;
import com.cas.myapplication.controladores.DirectorControler;
import com.cas.myapplication.directorVistaPrincipal;
import com.cas.myapplication.models.Director;
import com.cas.myapplication.models.Profesor;
import com.cas.myapplication.profesorVistaPrincipal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class InicioSesionDialog extends DialogFragment {
    int from;
    boolean estado;
    String keyId;
    public InicioSesionDialog(int from){
        this.from=from;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_1,container,false);
        final EditText user= view.findViewById(R.id.loginUserLog1);
        final EditText password = view.findViewById(R.id.loginPasswordLog1);
        final Button ok = view.findViewById(R.id.login_btn_ok);
        final Button cancel = view.findViewById(R.id.login_btn_cancel);
        estado=false;
        final DirectorControler control = new DirectorControler();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id, pass;
                id = user.getText().toString();
                pass=password.getText().toString();

                if (from==0){
                    control.getDirectores().child("Director").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                Director aux= data.getValue(Director.class);
                                if(aux.getCorreo().equals(id) && aux.getPassword().equals(pass)){
                                    estado=true;
                                    keyId = aux.getIdDirector();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    control.getDirectores().child("Profesor").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                Profesor aux= data.getValue(Profesor.class);
                                if(aux.getDni().equals(id) && aux.getPassword().equals(pass)){
                                    estado=true;
                                    keyId=aux.getDni();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                if(estado && from==0){
                    Intent intent = new Intent(view.getContext(), directorVistaPrincipal.class);
                    intent.putExtra("ID",keyId);
                    startActivityForResult(intent,0);
                    getDialog().dismiss();
                }else if(estado && from ==1){
                    Intent intent = new Intent (v.getContext(), profesorVistaPrincipal.class);
                    intent.putExtra("ID",keyId);
                    startActivityForResult(intent, 1);
                    getDialog().dismiss();
                }
                else{

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
