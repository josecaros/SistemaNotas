package com.cas.myapplication.resources.alumno;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Matricula;
import com.cas.myapplication.resources.profesor.ListenerOfDataBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptadorListaNotasAlumnos extends ArrayAdapter<Matricula> {
    private Context context;
    private List<Matricula> objects;
    private Curso cur = new Curso();

    public AdaptadorListaNotasAlumnos(@NonNull Context context, int resource, @NonNull List<Matricula> objects) {
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
            convertView = layoutInflater.inflate(R.layout.row_list_alumn_profesor, parent, false);
        }

        final Matricula matricula = getItem(position);
        TextView nombre = (TextView) convertView.findViewById(R.id.matriAluNombre);
        nombre.setText((matricula.getNombreAlumno()));
        TextView nota = (TextView) convertView.findViewById(R.id.matriAluNota);
        nota.setText(matricula.getIdNota());
        TextView curso=(TextView)convertView.findViewById(R.id.matriAluCurso);
        curso.setText(matricula.getCurso());


        return convertView;
    }

}
