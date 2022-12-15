package com.example.finalproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class bniPayment extends AppCompatActivity implements QRGenerator{
    TextView TotalHarga,hargaTiket,numericgen,namabus,DEPARTUREDATE;
    ImageButton backtopayment;
    int code;
    Button verifAlfa;
    String uid;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            uid = currentUser.getUid();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bni_payment);

        backtopayment = findViewById(R.id.backtopayment);
        backtopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TotalHarga = (TextView) findViewById(R.id.TotalHarga);
        hargaTiket = (TextView) findViewById(R.id.hargaTiket);
        numericgen = (TextView) findViewById(R.id.numericgen);
        int hargaTemp = getIntent().getIntExtra("harga",0);
        TotalHarga.setText("Rp"+String.valueOf(hargaTemp));
        hargaTiket.setText(String.valueOf(hargaTemp));
        generate();
        numericgen.setText(Integer.toString(code));
        verifAlfa = (Button) findViewById(R.id.verifAlfa);

        String namaUser = getIntent().getStringExtra("nama");
        String genderUser = getIntent().getStringExtra("gender");
        String ageUser = getIntent().getStringExtra("age");
        String nomorUser = getIntent().getStringExtra("nomor");
        String jamAwal = getIntent().getStringExtra("jamAwal");
        String jamAkhir = getIntent().getStringExtra("jamAkhir");
        String terminalAwal = getIntent().getStringExtra("terminalAwal");
        String terminalAkhir = getIntent().getStringExtra("terminalAkhir");
        String namabis = getIntent().getStringExtra("namabis");
//        intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
//        intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
        verifAlfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("nama",namaUser);
                data.put("gender",genderUser);
                data.put("age",ageUser);
                data.put("nomor",nomorUser);
                data.put("jamAwal",jamAwal);
                data.put("jamAkhir",jamAkhir);
                data.put("namaAwal",terminalAwal);
                data.put("namaAkhir",terminalAkhir);
                data.put("nomorBo",Integer.toString(code));
                data.put("harga",hargaTemp);
                data.put("namabis",getIntent().getStringExtra("namabis"));
                data.put("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                data.put("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                FirebaseDatabase.getInstance().getReference().child("struk").child(uid).push().
                        setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(bniPayment.this,succesPayment.class);
                                intent.putExtra("nama",namaUser);
                                intent.putExtra("gender",genderUser);
                                intent.putExtra("age",ageUser);
                                intent.putExtra("nomor",nomorUser);
                                intent.putExtra("jamAwal",jamAwal);
                                intent.putExtra("jamAwal",jamAkhir);
                                intent.putExtra("namaAwal",terminalAwal);
                                intent.putExtra("namaAkhir",terminalAkhir);
                                intent.putExtra("harga",hargaTemp);
                                intent.putExtra("namabis",getIntent().getStringExtra("namabis"));
                                intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                                intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                                intent.putExtra("via","BNI");
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {

                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(bniPayment.this,"Data Gagal Ditambahkan",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void generate() {
        Random random = new Random();
        code = random.nextInt(9999999);
    }

}