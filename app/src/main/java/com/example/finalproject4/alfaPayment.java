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
import android.widget.EditText;
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

public class alfaPayment extends AppCompatActivity implements QRGenerator {
    TextView TotalHarga,numericgenAlfa;
    ImageButton backtopaymetns;
    ImageView qrcode;
    Button btn;
    int code;
    private FirebaseAuth mAuth;
    String email,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfa_payment);

        qrcode = findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alfaPayment.this,qrscan.class);
                intent.putExtra("code",Integer.toString(code));
                startActivity(intent);
            }
        });

        backtopaymetns = findViewById(R.id.backtopaymetns);
        backtopaymetns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TotalHarga = (TextView) findViewById(R.id.TotalHargaAlfa);
        numericgenAlfa = (TextView) findViewById(R.id.numericgenAlfa);
        int hargaTemp = getIntent().getIntExtra("harga",0);
        TotalHarga.setText("Rp"+String.valueOf(hargaTemp));
        generate();
        numericgenAlfa.setText(Integer.toString(code));

        String namaUser = getIntent().getStringExtra("nama");
        String genderUser = getIntent().getStringExtra("gender");
        String ageUser = getIntent().getStringExtra("age");
        String nomorUser = getIntent().getStringExtra("nomor");
        String jamAwal = getIntent().getStringExtra("jamAwal");
        String jamAkhir = getIntent().getStringExtra("jamAkhir");
        String terminalAwal = getIntent().getStringExtra("terminalAwal");
        String terminalAkhir = getIntent().getStringExtra("terminalAkhir");

        btn = findViewById(R.id.verifAlfa);
        btn.setOnClickListener(new View.OnClickListener() {
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
                data.put("harga",hargaTemp);
                data.put("nomorBo",Integer.toString(code));
                data.put("namabis",getIntent().getStringExtra("namabis"));
                data.put("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                data.put("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                FirebaseDatabase.getInstance().getReference().child("struk").child(uid).push().
                        setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(alfaPayment.this,"Berhasil Membeli",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(alfaPayment.this,TicketDetail.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {

                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(alfaPayment.this,"Data Gagal Ditambahkan",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            email = currentUser.getEmail();
            uid = currentUser.getUid();
        }
    }

    @Override
    public void generate() {
        Random random = new Random();
        code = random.nextInt(9999999);
    }
}