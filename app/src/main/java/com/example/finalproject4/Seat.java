package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Seat extends AppCompatActivity implements View.OnClickListener{
    TextView nama,type,totalan;
    Button buy;
    ImageButton backHomeDetail;
    RelativeLayout a1,a2,a3,a4,a5,b1,b2,b3,b4,b5,c1,c2,c3,c4,c5;
    String A1 = "#FF6200EE" ,
            A2 = "#FF6200EE" ,
            A3 = "#FF6200EE" ,
            A4 = "#FF6200EE",
            A5= "#FF6200EE",
            B1 = "#FF6200EE",
            B2 = "#FF6200EE",
            B3 = "#FF6200EE",
            B4 = "#FF6200EE",
            B5 = "#FF6200EE",
            C1 = "#FF6200EE",
            C2 = "#FF6200EE",
            C3 = "#FF6200EE",
            C4 = "#FF6200EE",
            C5 = "#FF6200EE" ;
    int harga = 0 ;
    int tampungHarga = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        backHomeDetail = findViewById(R.id.backHomeDetail);
        backHomeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nama = (TextView) findViewById(R.id.NamaDetail);
        type = (TextView) findViewById(R.id.TypeDetail);
        int tiket = getIntent().getIntExtra("hargaTiket",0);
        tampungHarga = tiket;
        String name = getIntent().getStringExtra("nama");
        String tipe = getIntent().getStringExtra("type");
        nama.setText(name);
        type.setText(tipe);
        totalan = (TextView) findViewById(R.id.TOTALHARGA);

        a1 = (RelativeLayout) findViewById(R.id.A1);
        a2 = (RelativeLayout) findViewById(R.id.A2);
        a3 = (RelativeLayout) findViewById(R.id.A3);
        a4 = (RelativeLayout) findViewById(R.id.A4);
        a5 = (RelativeLayout) findViewById(R.id.A5);

        b1 = (RelativeLayout) findViewById(R.id.B1);
        b2 = (RelativeLayout) findViewById(R.id.B2);
        b3 = (RelativeLayout) findViewById(R.id.B3);
        b4 = (RelativeLayout) findViewById(R.id.B4);
        b5 = (RelativeLayout) findViewById(R.id.B5);

        c1 = (RelativeLayout) findViewById(R.id.C1);
        c2 = (RelativeLayout) findViewById(R.id.C2);
        c3 = (RelativeLayout) findViewById(R.id.C3);
        c4 = (RelativeLayout) findViewById(R.id.C4);
        c5 = (RelativeLayout) findViewById(R.id.C5);

        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        a5.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);

        buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                String jamAwal = getIntent().getStringExtra("estimasiAwal");
                String jamAkhir = getIntent().getStringExtra("estimasiAkhir");
                String namaAwal = getIntent().getStringExtra("namaAwal");
                String namaAkhir = getIntent().getStringExtra("namaAkhir");
                Intent intent = new Intent(this,contact.class);
                intent.putExtra("namabis",getIntent().getStringExtra("nama"));
                intent.putExtra("harga",harga);
                intent.putExtra("estimasiAwal",jamAwal);
                intent.putExtra("estimasiAkhir",jamAkhir);
                intent.putExtra("namaAwal",namaAwal);
                intent.putExtra("namaAkhir",namaAkhir);
                intent.putExtra("tanggalAwal",getIntent().getStringExtra("tanggalAwal"));
                intent.putExtra("tanggalKembali",getIntent().getStringExtra("tanggalKembali"));
                startActivity(intent);
                break;
            case R.id.A1:
                if (A1.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    A1 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    A1 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.A2:
                if (A2.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    A2 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    A2 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.A3:
                if (A3.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    A3 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    A3 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.A4:
                if (A4.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    A4 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    A4 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.A5:
                if (A5.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    A5 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    A5 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.B1:
                if (B1.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    B1 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    B1 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.B2:
                if (B2.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    B2 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    B2 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.B3:
                if (B3.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    B3 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    B3 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.B4:
                if (B4.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    B4 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    B4 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.B5:
                if (B5.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    B5 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    B5 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.C1:
                if (C1.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    C1 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    C1 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.C2:
                if (C2.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    C2 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    C2 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.C3:
                if (C3.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    C3 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    C3 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.C4:
                if (C4.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    C4 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    C4 = "#FF6200EE";
                    kurang();
                }
                break;
            case R.id.C5:
                if (C5.equals("#FF6200EE")){
                    v.setBackgroundColor(Color.RED);
                    C5 = "0xFFFF0000";
                    tambah();
                }else{
                    v.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    C5 = "#FF6200EE";
                    kurang();
                }
                break;
        }
    }

    private void tambah() {
        harga += tampungHarga;
        System.out.println(harga);
        totalan.setText( "RP"+ String.valueOf(harga));
    }
    private void kurang(){
        harga -= tampungHarga;
        System.out.println(harga);
        totalan.setText("RP"+ String.valueOf(harga));
    }
}