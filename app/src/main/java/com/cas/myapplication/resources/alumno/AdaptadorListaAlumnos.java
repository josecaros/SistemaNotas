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
import com.cas.myapplication.users.Alumno;
import com.cas.myapplication.users.Profesor;

import java.util.List;

public class AdaptadorListaAlumnos extends ArrayAdapter<Alumno> {
    private Context context;
    private List<Alumno> objects;
    private AlumnoControler alumnoControler = new AlumnoControler();


    public AdaptadorListaAlumnos(@NonNull Context context, int resource, @NonNull List<Alumno> objects) {
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
            convertView = layoutInflater.inflate(R.layout.row_alumno, parent, false);
        }

        final Alumno alumno = getItem(position);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewNombreAlumno);
        nombre.setText((alumno.getNombre()));
        TextView apellido = (TextView) convertView.findViewById(R.id.textViewApellidoAlumno);
        apellido.setText(alumno.getApellido());
        TextView dni = (TextView) convertView.findViewById(R.id.textViewDNIAlumno);
        dni.setText(alumno.getIdAlumno());
        return convertView;
    }
}
