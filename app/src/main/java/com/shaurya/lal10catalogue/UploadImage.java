package com.shaurya.lal10catalogue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadImage extends AppCompatActivity {

    private Button selectimage;
    private ImageView showImage;

    private StorageReference mstorageRef;
    private ProgressDialog mprogress;

    private static final int GALLERY_INTENT=2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT&& resultCode==RESULT_OK){

            mprogress.setMessage("Uploading....");
            mprogress.show();

            Uri uri=data.getData();

            final StorageReference filepath=mstorageRef.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(UploadImage.this,"Upload Done !",Toast.LENGTH_LONG).show();
                    mprogress.dismiss();

                    //Uri downloaduri=StorageReference.get
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloaduri=uri;
                            Picasso.with(UploadImage.this).load(downloaduri).fit().centerCrop().into(showImage);
                            Toast.makeText(UploadImage.this,"Image Display",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectimage=findViewById(R.id.uploadimage);
        mprogress=new ProgressDialog(this);

        showImage=findViewById(R.id.imageView);
        mstorageRef= FirebaseStorage.getInstance().getReference();

        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent((Intent.ACTION_PICK));

                intent.setType("image/*");

                startActivityForResult(intent,GALLERY_INTENT);

            }
        });


    }
}
