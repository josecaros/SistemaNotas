package com.cas.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.cas.myapplication.fragment.DirectorAlumnoFragment;
import com.cas.myapplication.fragment.DirectorCursoFragment;
import com.cas.myapplication.fragment.DirectorProfesorFragment;
import com.cas.myapplication.resources.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class directorVistaPrincipal extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_vista_principal);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.myViewPager);

        Log.i("tab","toolbar");
        setSupportActionBar(toolbar);
        Log.i("tab","viewpager");
        setupViewPager(viewPager);
        Log.i("tab","tablayout");
        tabLayout.setupWithViewPager(viewPager);
        Log.i("tab","finMetodo");
    }

    private void setupViewPager(ViewPager viewPager){
        Log.i("tab","metodo");
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DirectorProfesorFragment(),"Profesores");
        viewPagerAdapter.addFragment(new DirectorAlumnoFragment(),"Alumnos");
        viewPagerAdapter.addFragment(new DirectorCursoFragment(),"Cursos");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
