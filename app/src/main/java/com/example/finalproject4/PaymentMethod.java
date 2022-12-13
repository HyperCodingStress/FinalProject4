package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class PaymentMethod extends AppCompatActivity implements View.OnClickListener{
    TextView bni,alfa,TotalHarga;
    int harga;
    ImageButton backtometode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        backtometode = findViewById(R.id.backtometode);
        backtometode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bni = (TextView) findViewById(R.id.BniPayment);
        alfa = (TextView) findViewById(R.id.alfaPayment);
        TotalHarga = (TextView) findViewById(R.id.TotalHarga);
        int hargaTemp = getIntent().getIntExtra("harga",0);
        harga = hargaTemp;
        TotalHarga.setText(String.valueOf("Rp"+hargaTemp));
        bni.setOnClickListener(this);
        alfa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String namaUser = getIntent().getStringExtra("nama");
        String genderUser = getIntent().getStringExtra("gender");
        String ageUser = getIntent().getStringExtra("age");
        String nomorUser = getIntent().getStringExtra("nomor");
        String jamAwal = getIntent().getStringExtra("jamAwal");
        String jamAkhir = getIntent().getStringExtra("jamAkhir");
        String terminalAwal = getIntent().getStringExtra("terminalAwal");
        String terminalAkhir = getIntent().getStringExtra("terminalAkhir");

        switch (v.getId()){
            case R.id.BniPayment:
                Intent intent = new Intent(this,bniPayment.class);
                intent.putExtra("harga",harga);
                intent.putExtra("nama" , namaUser);
                intent.putExtra("namabis",getIntent().getStringExtra("namabis"));
                intent.putExtra("gender" , genderUser);
                intent.putExtra("age",ageUser);
                intent.putExtra("nomorUser",nomorUser);
                intent.putExtra("jamAwal" , jamAwal);
                intent.putExtra("jamAkhir",jamAkhir);
                intent.putExtra("terminalAwal" , terminalAwal);
                intent.putExtra("terminalAkhir",terminalAkhir);
                intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                startActivity(intent);
                break;
            case R.id.alfaPayment:
                Intent Intent = new Intent(this,alfaPayment.class);
                Intent.putExtra("harga",harga);
                Intent.putExtra("nama" , namaUser);
                Intent.putExtra("namabis",getIntent().getStringExtra("namabis"));
                Intent.putExtra("gender" , genderUser);
                Intent.putExtra("age",ageUser);
                Intent.putExtra("nomorUser",nomorUser);
                Intent.putExtra("jamAwal" , jamAwal);
                Intent.putExtra("jamAkhir",jamAkhir);
                Intent.putExtra("terminalAwal" , terminalAwal);
                Intent.putExtra("terminalAkhir",terminalAkhir);
                Intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                Intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                startActivity(Intent);
                break;
        }
    }
}