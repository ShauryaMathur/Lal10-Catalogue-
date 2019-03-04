package com.shaurya.lal10catalogue;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    private ListView mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mlist=findViewById(R.id.listview);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://lal10-catalogue.firebaseio.com/Products");


        FirebaseListAdapter<String> firebaselistadapter=new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,databaseReference) {
            @Override
            protected void populateView(@NonNull View v, @NonNull String model, int position) {

                TextView textview=(TextView)v.findViewById(android.R.id.text1);
                textview.setText(model);

            }
        };

        mlist.setAdapter(firebaselistadapter);


    }
}
