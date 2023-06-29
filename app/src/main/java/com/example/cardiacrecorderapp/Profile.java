package com.example.cardiacrecorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private FirebaseUser firebaseuser;
    private DatabaseReference reference;
    private String userID;
    private ProgressBar progressBar;
    private Button button;
    private ImageButton Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final TextView greeting = (TextView) findViewById(R.id.greeting);
        final TextView Fullname = (TextView) findViewById(R.id.Fullname);
        final TextView Email = (TextView) findViewById(R.id.Email);
        final  TextView Username = (TextView) findViewById(R.id.Username);
        final TextView Contact_number = (TextView) findViewById(R.id.Contact_number);
        final TextView DOB = (TextView) findViewById(R.id.DOB);
        button = (Button) findViewById(R.id.update);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Update_Profile();
            }
        });
        Exit = (ImageButton)findViewById(R.id.exit);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("user");
        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseuser.getUid();
        reference.child(userID).child("user information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User_info userprofile = snapshot.getValue(User_info.class);
                if (userprofile != null) {
                    progressBar.setVisibility(View.GONE);
                    String username = userprofile.Username;
                    String fullname = userprofile.name;
                    String email = userprofile.email;
                    String contact_number = userprofile.phone_number;
                    String dob = userprofile.birthday;

                    greeting.setText("Welcome to your Profile " + username);
                    Fullname.setText(fullname);
                    Email.setText(email);
                    Username.setText(username);
                    Contact_number.setText(contact_number);
                    DOB.setText(dob);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(Profile.this, "Something Wrong Happened!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Update_Profile()
    {
        Toast.makeText(Profile.this, "Update your Profile!.", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(Profile.this, update_profile.class);
        startActivity(intent);
    }

    }