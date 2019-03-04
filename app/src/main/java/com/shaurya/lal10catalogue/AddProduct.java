package com.shaurya.lal10catalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class AddProduct extends AppCompatActivity {

    private Button addtoproducts;
    private EditText getname,getcategory,getdesc,getprice;
    private Firebase mRootref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootref=new Firebase("https://lal10-catalogue.firebaseio.com/Products");

        getname=findViewById(R.id.productname);
        getcategory=findViewById(R.id.productcategory);
        getdesc=findViewById(R.id.productdesc);
        getprice=findViewById(R.id.productprice);

        addtoproducts=findViewById(R.id.addtodb);

        addtoproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Firebase mrefchild=mref.child("Name");
                //mrefchild.setValue(getname.getText().toString());

                Product prod=new Product();
                String productSerialKey=mRootref.push().getKey();

                prod.setName(getname.getText().toString());
                prod.setCategory(getcategory.getText().toString());
                prod.setDesc(getdesc.getText().toString());
                prod.setPrice(getprice.getText().toString());
                prod.setProductKey(productSerialKey);


                Firebase product=mRootref.child(productSerialKey);
                product.setValue(prod);
            }
        });


    }
}
