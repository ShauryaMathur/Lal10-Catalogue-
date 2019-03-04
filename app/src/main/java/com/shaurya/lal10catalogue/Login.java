package com.shaurya.lal10catalogue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email,password;
    private Button login;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private void startSignIn(){

        String emailvalue=email.getText().toString();
        String passwordvalue=password.getText().toString();

        if(TextUtils.isEmpty(emailvalue)||TextUtils.isEmpty(passwordvalue)){

            Toast.makeText(Login.this,"Fields are empty!",Toast.LENGTH_LONG).show();

        }
        else{

            mAuth.signInWithEmailAndPassword(emailvalue,passwordvalue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful())
                        Toast.makeText(Login.this,"Sign In Error!",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    //If you want app to stay signed in
    
    /*@Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        login=findViewById(R.id.login);



        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(Login.this,Profile.class));
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();

            }
        });


    }
}
