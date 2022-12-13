package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailPayment extends AppCompatActivity {
    TextView nama,nomor,busnama,jamBerangkat,
            jamPulang,namaAwal,namaAkhir,hargaDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);

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
        nama.setText(namaUser);
        nomor.setText(nomorUser);
        hargaDetail.setText("Rp"+String.valueOf(harga));
    }
}