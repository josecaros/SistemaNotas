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
import com.cas.myapplication.controladores.AlumnoControler;
import com.cas.myapplication.resources.alumno.ActualizarAlumnoDialog;
import com.cas.myapplication.resources.alumno.AdaptadorListaAlumnos;
import com.cas.myapplication.resources.alumno.InfoAlumnoDialog;
import com.cas.myapplication.resources.alumno.RegistrarAlumno;
import com.cas.myapplication.resources.profesor.InfoProfesorDialog;
import com.cas.myapplication.models.Alumno;
import com.cas.myapplication.models.Profesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorAlumnoFragment extends Fragment {
    ArrayList<Alumno> alumnos = new ArrayList<>();
    AdaptadorListaAlumnos adapter;
    ListView lista;
    Button btn_añadir;
    private AlumnoControler alumnoControler = new AlumnoControler();
    public DirectorAlumnoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vista =inflater.inflate(R.layout.fragment_director_alumno, container, false);
        lista = (ListView) vista.findViewById(R.id.list_view_alumnos);
        btn_añadir = (Button) vista.findViewById(R.id.btn_regNuevoAlumno);
        btn_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , RegistrarAlumno.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
                Log.i("MainActivity", "Abriendo otro activity");
            }
        });
        alumnos = alumnoControler.getAll();
        Log.i("tab","PRofesorFragmenaaalumnoa3");
        listarDatos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoAlumnoDialog info = new InfoAlumnoDialog((Alumno) lista.getItemAtPosition(position));
                info.show(getFragmentManager(), "InfoProfesorDialog");
            }
        });
        Log.i("tab","PRofesorFragmenaaalumnoa323Alumno");
        registerForContextMenu(lista);
        Log.i("tab","PRofesorFragmenaaalumnoa234");
        return vista;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_alumno, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.acticonMenuModificarAlumno){
            ActualizarAlumnoDialog actualizar  = new ActualizarAlumnoDialog(alumnos.get(info.position), alumnoControler);
            actualizar.show(getFragmentManager(),"Actualizar");
            listarDatos();

        }else if(item.getItemId() == R.id.acticonMenuDeleteAlumno){
            Alumno alumno= alumnos.get(info.position);
            alertaEliminar(alumno);
        }

        return super.onContextItemSelected(item);
    }

    private void listarDatos(){
        alumnoControler.getAlumnos().child("Alumno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alumnos.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Alumno aux = objtSnap.getValue(Alumno.class);
                    alumnos.add(aux);
                    adapter = new AdaptadorListaAlumnos(getContext(), R.id.list_view_alumnos, alumnos);
                    lista.setAdapter(adapter);
                }
                Log.i("tab","PRofesorFragmenaaaa3ssssAlumno");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void alertaEliminar(final Alumno alumno){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Confirmar Eliminacion");
        alert.setMessage("Deseas eliminar a "+alumno.getNombre()).
                setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alumnoControler.eliminarAlumno(alumno.getIdAlumno());
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
