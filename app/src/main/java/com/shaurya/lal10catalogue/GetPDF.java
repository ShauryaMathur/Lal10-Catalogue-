package com.shaurya.lal10catalogue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class GetPDF extends AppCompatActivity {

    //private ArrayList<Product> prodlist1=new ArrayList<Product>();

    private boolean mProcessSwitch=false;

    private RecyclerView productlist;

    private DatabaseReference mDatabaseRef;

    private DatabaseReference mDatabasePDF;

    final String[] post = new String[20];

    //private SearchProductAdapter adapter;

   // private String searchText;


    @Override
    protected void onStart() {
        super.onStart();

        //mDatabasePDF.addC

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    post[i] = postSnapshot.getKey();
                    Log.v("Get Data ->", post[i]);

                    FirebaseRecyclerAdapter<Product, GetAllProductsCardLayout.ProductViewHolder> firebaserecycleradapter = new FirebaseRecyclerAdapter<Product, GetAllProductsCardLayout.ProductViewHolder>(

                            Product.class,
                            R.layout.pdf_product_row,
                            GetAllProductsCardLayout.ProductViewHolder.class,
                            mDatabasePDF
                    ) {
                        @Override
                        public void populateViewHolder(GetAllProductsCardLayout.ProductViewHolder viewHolder, Product model, int position) {

                            final String product_key = getRef(position).getKey();

                            viewHolder.setName(model.getName());
                            viewHolder.setCategory(model.getCategory());
                            viewHolder.setDescription(model.getDesc());
                            viewHolder.setPrice(model.getPrice());
                            viewHolder.setImage(getApplicationContext(), model.getImage());


                            //prodlist1.add(model);
                            //Toast.makeText(getApplicationContext(),prodlist1.toString(),Toast.LENGTH_LONG).show();

                            viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    //Toast.makeText(GetAllProductsCardLayout.this,product_key,Toast.LENGTH_LONG).show();

                                    Intent singleproductintent = new Intent(GetPDF.this, ProductSingleActivity.class);
                                    singleproductintent.putExtra("Product_key", product_key);
                                    startActivity(singleproductintent);

                                }
                            });

                            viewHolder.addtopdfswitch.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mProcessSwitch = true;
                                    mDatabasePDF.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (mProcessSwitch) {

                                                if (dataSnapshot.hasChild(product_key)) {

                                                    mDatabasePDF.child(product_key).removeValue();
                                                    mProcessSwitch = false;
                                                } else {

                                                    mDatabasePDF.child(product_key).setValue("Random Value");
                                                    mProcessSwitch = false;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            });
                        }
                    };
                    //firebaserecycleradapter.notifyItemInserted((int)dataSnapshot.getChildrenCount());
                    productlist.setAdapter(firebaserecycleradapter);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mDatabasePDF.addListenerForSingleValueEvent(valueEventListener);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.selected_product_toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productlist=findViewById(R.id.selectedproductlist);
        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Products");
        mDatabasePDF=FirebaseDatabase.getInstance().getReference().child("ProductPDF");
        //mDatabaseRef.keepSynced(true);
        //mDatabasePDF.keepSynced(true);

        productlist.setHasFixedSize(true);
        productlist.setLayoutManager(new LinearLayoutManager(this));

        /*final String product_key=mDatabasePDF.child();
        Log.v("Some 2:",product_key);*/

        //adapter=new SearchProductAdapter(getApplicationContext(),prodlist1);


    }


}

