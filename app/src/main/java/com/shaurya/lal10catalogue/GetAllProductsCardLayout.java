package com.shaurya.lal10catalogue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class GetAllProductsCardLayout extends AppCompatActivity {

    private RecyclerView productlist;

    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_products_card_layout);

        productlist=findViewById(R.id.productlist);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Products");
        mDatabaseRef.keepSynced(true);

        productlist.setHasFixedSize(true);
        productlist.setLayoutManager(new LinearLayoutManager(this));
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
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setCategory(model.getCategory());
                viewHolder.setDescription(model.getDesc());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setImage(getApplicationContext(),model.getImage());

            }
        };

        productlist.setAdapter(firebaserecycleradapter);

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        View mview;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setName(String title){

            TextView product_name=mview.findViewById(R.id.product_name);
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
}
