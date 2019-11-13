package com.cas.myapplication.resources.cursos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Profesor;

import java.util.List;

public class AdaptadorListaCursos extends ArrayAdapter<Curso> {
    private Context context;
    private List<Curso> objects;
    private CursoControler acciones = new CursoControler();

    public AdaptadorListaCursos(@NonNull Context context, int resource, @NonNull List<Curso> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_curso, parent, false);
        }

        final Curso curso = getItem(position);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewNombreCurso);
        nombre.setText((curso.getNombre()));
        return convertView;
    }
}
