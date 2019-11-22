package com.cas.myapplication.resources.alumno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.models.Alumno;

import java.util.List;

public class AdaptadorListaAlumnosPadre extends ArrayAdapter<Alumno> {

    private Context context;
    private List<Alumno> objects;
    private AlumnoControler alumnoControler = new AlumnoControler();

    public AdaptadorListaAlumnosPadre(@NonNull Context context, int resource, @NonNull List<Alumno> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_hijo, parent, false);
        }
        final Alumno alumno = getItem(position);
        TextView nombres = (TextView) convertView.findViewById(R.id.textViewHijoNombre);
        TextView apellidos = (TextView) convertView.findViewById(R.id.textViewHijoApedllido);
        TextView edad = (TextView) convertView.findViewById(R.id.textViewHijoEdad);

        nombres.setText((alumno.getNombre()));
        apellidos.setText((alumno.getApellido()));
        edad.setText((alumno.getEdad()));

        return convertView;
    }
}
