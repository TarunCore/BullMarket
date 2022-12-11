package com.tweshllc.kskdhallmill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail,edtPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private ImageView backBtn;
    ProgressDialog pg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail=findViewById(R.id.edtEmailLogin);
        edtPassword=findViewById(R.id.edtPasswordLogin);
        btnLogin=findViewById(R.id.btnLogin);
        backBtn=findViewById(R.id.btnBack2);
        btnLogin.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
        pg = new ProgressDialog(Login.this);
        pg.setTitle("Authentication");
        pg.setMessage("Connecting to Bull Market server...");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.super.onBackPressed();
                //Intent intent=new Intent(Login.this,SignUp.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (edtEmail.getText().toString().equals("")||edtPassword.getText().toString().equals(("")))
        {
            Toast.makeText(Login.this, "All fields are required",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            pg.show();
            mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Successfully logged!!",
                                        Toast.LENGTH_SHORT).show();
                                pg.dismiss();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Error: "+task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();
                                pg.dismiss();
                            }
                        }
                    });
        }
    }


}