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
import com.cas.myapplication.controladores.ProfesorControler;
import com.cas.myapplication.resources.profesor.ActualizarProfersorDialog;
import com.cas.myapplication.resources.profesor.AdaptadorListaProfesores;
import com.cas.myapplication.resources.profesor.InfoProfesorDialog;
import com.cas.myapplication.resources.profesor.RegistrarProfesor;
import com.cas.myapplication.models.Profesor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorProfesorFragment extends Fragment {
    ArrayList<Profesor> profesores = new ArrayList<>();
    AdaptadorListaProfesores adapter;
    ListView lista;
    Button btn_añadir;
    private ProfesorControler profesorControler = new ProfesorControler();
    public DirectorProfesorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =inflater.inflate(R.layout.fragment_director_profesor, container, false);
        Log.i("tab","PRofesorFragmenaaaa");
        lista = (ListView) vista.findViewById(R.id.list_view_profesores);
        Log.i("tab","PRofesorFragmenaaaa2");
        btn_añadir = (Button) vista.findViewById(R.id.btn_regNuevoProfesor);
        Log.i("tab","PRofesorFragmenaaaa3");
        btn_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , RegistrarProfesor.class);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
                Log.i("MainActivity", "Abriendo otro activity");
            }
        });

        profesores = profesorControler.getAll();
        Log.i("tab","PRofesorFragmenaaaa3");
        listarDatos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoProfesorDialog info = new InfoProfesorDialog((Profesor)lista.getItemAtPosition(position));
                info.show(getFragmentManager(), "InfoProfesorDialog");
            }
        });
        registerForContextMenu(lista);
        Log.i("tab","PRofesorFragmenaaalumnoa3Profesor");
        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_profesor, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.acticonMenuModificar){
            ActualizarProfersorDialog actualizar  = new ActualizarProfersorDialog(profesores.get(info.position), profesorControler);
            actualizar.show(getFragmentManager(),"Actualizar");
            listarDatos();

        }else if(item.getItemId() == R.id.acticonMenuDelete){
            Profesor profesor= profesores.get(info.position);
            alertaEliminar(profesor);
        }

        return super.onContextItemSelected(item);
    }

    private void listarDatos(){
        Log.i("tab","PRofesorFragmenaaaa3");
        profesorControler.getProfesores().child("Profesor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profesores.clear();
                for(DataSnapshot objtSnap: dataSnapshot.getChildren()){
                    Profesor aux = objtSnap.getValue(Profesor.class);
                    profesores.add(aux);
                    adapter = new AdaptadorListaProfesores(getContext(), R.id.list_view_profesores, profesores);
                    lista.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void alertaEliminar(final Profesor profesor){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Confirmar Eliminacion");
        alert.setMessage("Deseas eliminar a "+profesor.getNombre()).
                setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        profesorControler.eliminarProfesor(profesor.getDni());
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
