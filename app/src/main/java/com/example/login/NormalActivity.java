package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NormalActivity extends AppCompatActivity {
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private TextView TextfullName,TextEmail,TextAge;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        logout = findViewById(R.id.logout);
        TextfullName = findViewById(R.id.fullname2);
        TextAge = findViewById(R.id.AgeId);
        TextEmail = findViewById(R.id.Email1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(NormalActivity.this,MainActivity.class));
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        fab = findViewById(R.id.fab); // ProfileActivity Button

        fab.setOnClickListener(new View.OnClickListener() { // If we click the Floating point button, we'll redirect to ProfileActivity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NormalActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });



//        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile = snapshot.getValue(User.class);
//                if(userProfile != null){
//                    String email = userProfile.email;
//                    String age = userProfile.age;
//
//                    TextEmail.setText(email);
//                    TextAge.setText(age);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(NormalActivity.this, "Something wrong happened!!!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}