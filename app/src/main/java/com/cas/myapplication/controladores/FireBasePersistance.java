package com.cas.myapplication.controladores;

import com.google.firebase.database.FirebaseDatabase;

public class FireBasePersistance extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
