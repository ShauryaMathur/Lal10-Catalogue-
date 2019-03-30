package com.shaurya.lal10catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class CatalogueActivity extends AppCompatActivity {

    GridView grid;
    String[] catgegory={"Hotels","Corporate Gifting","Export Houses","Boutiques/Designers","Exhibitions","CSR/Philanthropy"};
    int[] imageId={R.drawable.hotels,R.drawable.corporate_gifting,R.drawable.export_houses,R.drawable.boutiques,R.drawable.exhibitions,R.drawable.csr_philanthropy,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridViewAdapter adapter=new GridViewAdapter(CatalogueActivity.this,catgegory,imageId);
        grid= findViewById(R.id.gridview);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CatalogueActivity.this,"You clicked at" + catgegory[+ i], Toast.LENGTH_LONG).show();

                switch (i){
                    case 0:{
                        startActivity(new Intent(CatalogueActivity.this,Hotels.class));
                        break;}

                    case 1:{
                        startActivity(new Intent(CatalogueActivity.this,CorporateGifting.class));
                        break;
                    }
                    case 2:{
                        startActivity(new Intent(CatalogueActivity.this,ExportHouses.class));
                        break;
                    }
                    case 3:{
                        startActivity(new Intent(CatalogueActivity.this,Boutiques_Designers.class));
                        break;
                    }
                    case 4:{
                        startActivity(new Intent(CatalogueActivity.this,Exhibitions.class));
                        break;
                    }
                    case 5:{
                        startActivity(new Intent(CatalogueActivity.this,CSR_Philanthropy.class));
                        break;
                    }

                }

            }
        });
    }
}
