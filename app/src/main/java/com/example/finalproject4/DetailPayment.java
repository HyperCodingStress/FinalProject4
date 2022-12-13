package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailPayment extends AppCompatActivity {
    TextView nama,nomor,busnama,jamBerangkat,
            jamPulang,namaAwal,namaAkhir,hargaDetail;
    Button btn;
    ImageButton backToContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);
        backToContact = findViewById(R.id.backToContact);
        backToContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nomor = (TextView) findViewById(R.id.noContact);
        nama = (TextView) findViewById(R.id.nameContact);
        busnama = (TextView) findViewById(R.id.busContact);
        jamBerangkat = (TextView) findViewById(R.id.jamAwal);
        jamPulang = (TextView) findViewById(R.id.jamAkhir);
        namaAwal = (TextView) findViewById(R.id.namaAwal);
        namaAkhir = (TextView) findViewById(R.id.namaAkhir);
        hargaDetail = (TextView) findViewById(R.id.hargaDetail);

        int harga = getIntent().getIntExtra("harga",0);
        String namaUser = getIntent().getStringExtra("nama");
        String genderUser = getIntent().getStringExtra("gender");
        String ageUser = getIntent().getStringExtra("age");
        String nomorUser = getIntent().getStringExtra("nomor");
        String jamAwal = getIntent().getStringExtra("estimasiAwal");
        String jamAkhir = getIntent().getStringExtra("estimasiAkhir");
        String terminalAwal = getIntent().getStringExtra("namaAwal");
        String terminalAkhir = getIntent().getStringExtra("namaAkhir");
//        intent.putExtra("estimasiAwal",jamAwal);
//        intent.putExtra("estimasiAkhir",jamAkhir);
        nama.setText(namaUser);
        nomor.setText(nomorUser);
        hargaDetail.setText("Rp"+String.valueOf(harga));
        jamBerangkat.setText(jamAwal);
        jamPulang.setText(jamAkhir);
        namaAwal.setText(terminalAwal);
        namaAkhir.setText(terminalAkhir);

        btn = findViewById(R.id.PaymentMetode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPayment.this,PaymentMethod.class);
                intent.putExtra("nama" , namaUser);
                intent.putExtra("namabis",getIntent().getStringExtra("namabis"));
                intent.putExtra("harga",harga);
                intent.putExtra("gender" , genderUser);
                intent.putExtra("age",ageUser);
                intent.putExtra("jamAwal" , jamAwal);
                intent.putExtra("jamAkhir",jamAkhir);
                intent.putExtra("terminalAwal" , terminalAwal);
                intent.putExtra("terminalAkhir",terminalAkhir);
                intent.putExtra("nomor",nomorUser);
                intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                startActivity(intent);
            }
        });
    }
}