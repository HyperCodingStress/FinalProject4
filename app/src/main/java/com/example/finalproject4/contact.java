package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class contact extends AppCompatActivity {
    EditText email,nama,nomor,age;
    private FirebaseAuth mAuth;
    String emails,gender;
    Button booknow;
    RadioGroup rg;
    private RadioButton radioButton;
    int harga,checkgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        nomor = (EditText) findViewById(R.id.PhoneContact);
        nama = (EditText) findViewById(R.id.nameContact);
        age = (EditText) findViewById(R.id.age);
        booknow = (Button) findViewById(R.id.booknow);

        rg = (RadioGroup) findViewById(R.id.gender);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(contact.this,"Gender: " + radioButton.getText(),Toast.LENGTH_SHORT).show();
                gender = radioButton.getText().toString();
            }
        });

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaUser = nama.getText().toString();
                if (nomor.getText().toString().length() < 11 ) {
                    nomor.setError("Nomor Tidak Boleh Kurang Dari 11");
                    nomor.requestFocus();
                    return;
                }
                if(age.getText().toString().isEmpty()){
                    age.setError("Tidak Boleh Kosong");
                    age.requestFocus();
                    return;
                }
                if(namaUser.isEmpty()){
                    nama.setError("Nama Tidak Boleh Kosong");
                    nama.requestFocus();
                    return;
                }
                harga = getIntent().getIntExtra("harga",0);

                Intent intent = new Intent(contact.this,DetailPayment.class);
                intent.putExtra("nama",namaUser);
                intent.putExtra("harga",harga);
                intent.putExtra("gender",gender);
                intent.putExtra("nomor",nomor.getText().toString());
                intent.putExtra("age",age.getText().toString());
                String jamAwal = getIntent().getStringExtra("estimasiAwal");
                String jamAkhir = getIntent().getStringExtra("estimasiAkhir");
                String NamaAwal = getIntent().getStringExtra("namaAwal");
                String namaAkhir = getIntent().getStringExtra("namaAkhir");

                System.out.println("contact");
                System.out.println("nama : " + namaUser);
                intent.putExtra("namabis",getIntent().getStringExtra("namabis"));
                intent.putExtra("estimasiAwal",jamAwal);
                intent.putExtra("estimasiAkhir",jamAkhir);
                intent.putExtra("namaAwal",NamaAwal);
                intent.putExtra("namaAkhir",namaAkhir);
                intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
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