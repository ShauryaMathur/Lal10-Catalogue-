package com.shaurya.lal10catalogue;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

public class GetAllProductsCardLayout extends AppCompatActivity {

    private ArrayList<Product> prodlist1=new ArrayList<Product>();

    private boolean mProcessSwitch=false;

    private RecyclerView productlist;

    private DatabaseReference mDatabaseRef;

    private DatabaseReference mDatabasePDF;

    private SearchProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_products_card_layout);

        productlist=findViewById(R.id.productlist);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Products");
        mDatabasePDF=FirebaseDatabase.getInstance().getReference().child("ProductPDF");
        mDatabaseRef.keepSynced(true);
        mDatabasePDF.keepSynced(true);

        productlist.setHasFixedSize(true);
        productlist.setLayoutManager(new LinearLayoutManager(this));


        //adapter=new SearchProductAdapter(getApplicationContext(),prodlist1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Product,ProductViewHolder> firebaserecycleradapter=new FirebaseRecyclerAdapter<Product, ProductViewHolder>(

                Product.class,
                R.layout.product_row,
                ProductViewHolder.class,
                mDatabaseRef
        ) {
            @Override
            public void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

                final String product_key=getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setCategory(model.getCategory());
                viewHolder.setDescription(model.getDesc());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setImage(getApplicationContext(),model.getImage());

                prodlist1.add(model);
                //Toast.makeText(getApplicationContext(),prodlist1.toString(),Toast.LENGTH_LONG).show();

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(GetAllProductsCardLayout.this,product_key,Toast.LENGTH_LONG).show();

                        Intent singleproductintent=new Intent(GetAllProductsCardLayout.this,ProductSingleActivity.class);
                        singleproductintent.putExtra("Product_key",product_key);
                        startActivity(singleproductintent);

                    }
                });

                viewHolder.addtopdfswitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mProcessSwitch=true;
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

        productlist.setAdapter(firebaserecycleradapter);

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        View mview;

        TextView product_name;

        Switch addtopdfswitch;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            addtopdfswitch=mview.findViewById(R.id.addtopdf);

            product_name=mview.findViewById(R.id.product_name);

            product_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ProductsCardLayout","Some Text");
                }
            });
        }

        public void setName(String title){

            //TextView product_name=mview.findViewById(R.id.product_name);
            product_name.setText(title);

        }

        public void setDescription(String desc){

            TextView product_desc=mview.findViewById(R.id.product_desc);
            product_desc.setText(desc);

        }

        public void setCategory(String cat){

            TextView product_cat=mview.findViewById(R.id.product_category);
            product_cat.setText(cat);

        }

        public void setPrice(String price){

            TextView product_price=mview.findViewById(R.id.product_price);
            product_price.setText(price);

        }

        public void setImage(final Context ctx,final String image){

            final ImageView post_image=mview.findViewById(R.id.product_image);
            //Picasso.with(ctx).load(image).into(post_image);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(post_image);

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.searchicon,menu);

        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //SearchProductAdapter adapter;
                //adapter.getFilter().filter(newText);
                String searchText=newText.substring(0,1).toUpperCase();
                Query mQuery=mDatabaseRef.orderByChild("name").startAt(searchText).endAt(searchText+"\uf8ff");

                FirebaseRecyclerAdapter<Product,ProductViewHolder> s=new FirebaseRecyclerAdapter<Product,ProductViewHolder>(
                        Product.class,
                        R.layout.product_row,
                        ProductViewHolder.class,
                        mQuery
                ) {
                    @Override
                    protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

                        final String product_key=getRef(position).getKey();

                        viewHolder.setName(model.getName());
                        viewHolder.setCategory(model.getCategory());
                        viewHolder.setDescription(model.getDesc());
                        viewHolder.setPrice(model.getPrice());
                        viewHolder.setImage(getApplicationContext(),model.getImage());

                        //prodlist1.add(model);
                        //Toast.makeText(getApplicationContext(),prodlist1.toString(),Toast.LENGTH_LONG).show();

                        viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Toast.makeText(GetAllProductsCardLayout.this,product_key,Toast.LENGTH_LONG).show();

                                Intent singleproductintent=new Intent(GetAllProductsCardLayout.this,ProductSingleActivity.class);
                                singleproductintent.putExtra("Product_key",product_key);
                                startActivity(singleproductintent);

                            }
                        });

                        viewHolder.addtopdfswitch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                mProcessSwitch=true;
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

                productlist.setAdapter(s);

                //Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
