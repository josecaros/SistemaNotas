package com.cas.myapplication.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cas.myapplication.R;
import com.cas.myapplication.controladores.CursoControler;
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.models.Curso;
import com.cas.myapplication.models.Profesor;
import com.cas.myapplication.resources.cursos.ActualizarCursoDialog;
import com.cas.myapplication.resources.cursos.AdaptadorListaCursos;
import com.cas.myapplication.resources.cursos.InfoCursoDialog;
import com.cas.myapplication.resources.cursos.RegistrarCurso;
import com.cas.myapplication.resources.profesor.ActualizarProfersorDialog;
import com.cas.myapplication.resources.profesor.AdaptadorListaProfesores;
import com.cas.myapplication.resources.profesor.InfoProfesorDialog;
import com.cas.myapplication.resources.profesor.RegistrarProfesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorCursoFragment extends Fragment {
    ArrayList<Curso> cursos = new ArrayList<>();
    AdaptadorListaCursos adapter;
    ListView lista;
    Button btn_añadir;
    private CursoControler cursoControler = new CursoControler();
    public DirectorCursoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =inflater.inflate(R.layout.fragment_director_curso, container, false);
        //
        Log.i("tab","PRofesorFragmenaaalumnoa3Curso");
        lista = (ListView) vista.findViewById(R.id.list_view_cursos);
        btn_añadir = (Button) vista.findViewById(R.id.btn_regNuevoCurso);
        btn_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , RegistrarCurso.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
                Log.i("MainActivity", "Abriendo otro activity");
            }
        });

        cursos = cursoControler.getAll();
        listarDatos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoCursoDialog info = new InfoCursoDialog((Curso) lista.getItemAtPosition(position));
                info.show(getFragmentManager(), "InfoCursoDialog");
            }
        });
        Log.i("tab","PRofesorFragmenaaalumnoa3Curso");
        registerForContextMenu(lista);
        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_curso, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.acticonMenuModificarCurso){
            ActualizarCursoDialog actualizar  = new ActualizarCursoDialog(cursos.get(info.position), cursoControler);
            actualizar.show(getFragmentManager(),"Actualizar");
            listarDatos();

        }else if(item.getItemId() == R.id.acticonMenuDeleteCurso){
            Curso curso= cursos.get(info.position);
            alertaEliminar(curso);
        }

        return super.onContextItemSelected(item);
    }

    private void listarDatos(){
        cursoControler.getCursos().child("Curso").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cursos.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Curso aux = objtSnap.getValue(Curso.class);
                    cursos.add(aux);
                    adapter = new AdaptadorListaCursos(getContext(), R.id.list_view_cursos, cursos);
                    lista.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void alertaEliminar(final Curso curso){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Confirmar Eliminacion");
        alert.setMessage("Deseas eliminar el curso "+curso.getNombre()).
                setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cursoControler.eliminarCurso(curso.getIdCurso());
                        Toast.makeText(getActivity(), "Item Eliminado", Toast.LENGTH_SHORT);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Accion Cancelada", Toast.LENGTH_SHORT);
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 100) {
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getBaseContext(), "Añadido Correctamente", (Toast.LENGTH_LONG)).show();
            } else if (resultCode == 200) {
                Toast.makeText(getActivity().getBaseContext(), "Se cancelo la acción", (Toast.LENGTH_LONG)).show();
            }

        }
    }
}
