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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    private EditText edtUserName,edtEmail,edtPassword;
    private TextView txtAlreadyHave;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUserName=findViewById(R.id.edtUsername);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        txtAlreadyHave=findViewById(R.id.txtHave);
        btnRegister=findViewById(R.id.btnRegister);
        backBtn=findViewById(R.id.btnBack1);
        mAuth=FirebaseAuth.getInstance();
        ProgressDialog pg= new ProgressDialog(SignUp.this);
        pg.setTitle("Authentication");
        pg.setMessage("Account is being created in our servers....");
        userRef= FirebaseDatabase.getInstance().getReference().child("Users");

        txtAlreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.getText().toString().equals("")||edtEmail.getText().toString().equals("")||
                        edtPassword.getText().toString().equals(""))
                {
                    Toast.makeText(SignUp.this, "All fields are Mandatory", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pg.show();
                    mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        userRef.child(mAuth.getCurrentUser().getUid()).child("Name")
                                                .setValue(edtUserName.getText().toString());
                                        Toast.makeText(SignUp.this, edtUserName.getText().toString()+"Successfully logged In!", Toast.LENGTH_SHORT).show();
                                        pg.dismiss();
                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(SignUp.this,"Error"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        pg.dismiss();
                                    }
                                }
                            });
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.super.onBackPressed();

                //Intent intent=new Intent(SignUp.this,MainActivity.class);
                //startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //if (mAuth.getCurrentUser()!=null)
        //{
            //dunno why please try to remove the below

            //Intent intent = new Intent(SignUp.this, About.class);
            //startActivity(intent);
            //finish();
        //}
    }
}