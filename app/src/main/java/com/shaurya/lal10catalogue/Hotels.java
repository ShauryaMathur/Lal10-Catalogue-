package com.shaurya.lal10catalogue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Hotels extends AppCompatActivity {

    ListView subcatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subcatlist=findViewById(R.id.hotel_sub_category);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        String[] hotelsubcategories={"Gifting","Housekeeping","Restaurants"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hotelsubcategories);

        subcatlist.setAdapter(adapter);

        subcatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){

                    case 0:{
                        Toast.makeText(Hotels.this,"Selected Gifting",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });
    }
}
