package com.example.cardiacrecorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {

    private Button B4;
    private EditText email;
    private ProgressBar progressBar;
    private TextView Exit;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = (EditText) findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        B4 = (Button) findViewById(R.id.B4);
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_password();
            }
        });

        Exit=(TextView) findViewById(R.id.exit);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Forget_password.this, "Exit", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Forget_password.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void reset_password() {
        String Email = email.getText().toString().trim();

        if (Email.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please Provide valid email!");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Forget_password.this, "Check your email to reset password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Forget_password.this, "Try again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}