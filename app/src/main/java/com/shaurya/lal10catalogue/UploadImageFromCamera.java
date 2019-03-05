package com.shaurya.lal10catalogue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadImageFromCamera extends AppCompatActivity {

    private Button selectimage;

    private StorageReference mstorageRef;
    private ProgressDialog mprogress;

    private static final int CAMERA_REQUEST_CODE=1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST_CODE&& resultCode==RESULT_OK){

            mprogress.setMessage("Uploading Image....");
            mprogress.show();

            Uri uri=data.getData();

            StorageReference filepath=mstorageRef.child("Photos2").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(UploadImageFromCamera.this,"Upload Done !",Toast.LENGTH_LONG).show();
                    mprogress.dismiss();

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
        setContentView(R.layout.activity_upload_image_from_camera);

        selectimage=findViewById(R.id.upload);
        mprogress=new ProgressDialog(this);

        mstorageRef= FirebaseStorage.getInstance().getReference();

        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });


    }
}
