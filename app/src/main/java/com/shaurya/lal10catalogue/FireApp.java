package com.shaurya.lal10catalogue;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Older Version
        Firebase.setAndroidContext(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder builder=new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built=builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        //Newer Version
        /*if(!FirebaseApp.getApps(this).isEmpty[]){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/



    }
}
