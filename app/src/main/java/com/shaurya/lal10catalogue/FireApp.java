package com.shaurya.lal10catalogue;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Older Version
        Firebase.setAndroidContext(this);

        //Newer Version
        /*if(!FirebaseApp.getApps(this).isEmpty[]){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/



    }
}
