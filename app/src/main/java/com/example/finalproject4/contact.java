package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class contact extends AppCompatActivity {
    EditText email,nama,nomor,age;
    private FirebaseAuth mAuth;
    String emails;
    Button booknow;
    int harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        nomor = (EditText) findViewById(R.id.PhoneContact);
        nama = (EditText) findViewById(R.id.nameContact);
        age = (EditText) findViewById(R.id.age);
        booknow = (Button) findViewById(R.id.booknow);

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                harga = getIntent().getIntExtra("harga",0);
                Intent intent = new Intent(contact.this,PaymentMethod.class);
                intent.putExtra("harga",harga);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            emails = currentUser.getEmail();
            email = (EditText) findViewById(R.id.emailContact);
            email.setText(emails.toString().trim());
            System.out.println(emails);
        }
    }
}