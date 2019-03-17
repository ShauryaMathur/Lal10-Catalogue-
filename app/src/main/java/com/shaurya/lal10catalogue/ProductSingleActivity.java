package com.shaurya.lal10catalogue;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductSingleActivity extends AppCompatActivity {

    private TextView mSingleName,mSingleCategory,mSingleDesc,mSinglePrice;
    private ImageView mSingleImage;

    private String mProductKey=null;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_single);

        mDatabase=FirebaseDatabase.getInstance().getReference().child("Products");

        mSingleName=findViewById(R.id.single_product_name);
        mSingleCategory=findViewById(R.id.single_product_category);
        mSingleDesc=findViewById(R.id.single_product_desc);
        mSingleImage=findViewById(R.id.single_product_image);
        mSinglePrice=findViewById(R.id.single_product_price);

        mProductKey=getIntent().getExtras().getString("Product_key");
        Toast.makeText(ProductSingleActivity.this,mProductKey,Toast.LENGTH_LONG).show();

        mDatabase.child(mProductKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String product_name=(String)dataSnapshot.child("name").getValue();
                String product_category=(String)dataSnapshot.child("category").getValue();
                String product_desc=(String)dataSnapshot.child("desc").getValue();
                String product_price=(String)dataSnapshot.child("price").getValue();
                String product_image=(String)dataSnapshot.child("image").getValue();

                mSingleName.setText(product_name);
                mSingleCategory.setText(product_category);
                mSingleDesc.setText(product_desc);
                mSinglePrice.setText(product_price);

                Picasso.with(ProductSingleActivity.this).load(product_image).into(mSingleImage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
