package com.cas.myapplication.resources;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.users.Director;
import com.cas.myapplication.users.Profesor;

import java.util.List;

public class AdaptadorListaProfesores extends ArrayAdapter<Profesor>  {
    private Context context;
    private List<Profesor>objects;
    private ProfesorControler acciones = new ProfesorControler();

    public AdaptadorListaProfesores(@NonNull Context context, int resource, @NonNull List<Profesor> objects) {
        super(context, resource, objects);
        this.objects= objects;
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_profesor, parent,false);
        }

        final Profesor profesor =getItem(position);
        TextView nombre =(TextView) convertView.findViewById(R.id.textViewNombre);
        nombre.setText((profesor.getNombre()));
        TextView apellido = (TextView) convertView.findViewById(R.id.textViewApellido);
        apellido.setText(profesor.getApellidos());
        TextView dni = (TextView) convertView.findViewById(R.id.textViewDNI);
        dni.setText(profesor.getDni());
        /*
        ImageButton btnModificar = (ImageButton) convertView.findViewById(R.id.btn_modficarRow);
        ImageButton btnEliminar = (ImageButton) convertView.findViewById(R.id.btn_eliminarRow);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dasda", "accion boton");
                alerta(profesor, v);
            }
        });*/



        //objects.add(profesor);
        return convertView;
    }

    public void alerta(final Profesor profesor, View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getRootView().getContext());
        alert.setTitle("Confirmar Eliminacion");
        alert.setMessage("Deseas eliminar a "+profesor.getNombre()).
                setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        acciones.eliminarProfesor(profesor.getIdProfesor());
                        Toast.makeText(context.getApplicationContext(), "Item Eliminado", Toast.LENGTH_SHORT);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context.getApplicationContext(), "Accion Cancelada", Toast.LENGTH_SHORT);
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
