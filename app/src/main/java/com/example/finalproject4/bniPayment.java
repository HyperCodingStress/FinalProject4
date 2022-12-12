package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class bniPayment extends AppCompatActivity implements QRGenerator{
    TextView TotalHarga,hargaTiket,numericgen;
    int code;
    Button verifAlfa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bni_payment);

        TotalHarga = (TextView) findViewById(R.id.TotalHarga);
        hargaTiket = (TextView) findViewById(R.id.hargaTiket);
        numericgen = (TextView) findViewById(R.id.numericgen);

        TotalHarga.setText(getIntent().getStringExtra("harga"));
        hargaTiket.setText(getIntent().getStringExtra("harga"));
        numericgen.setText(Integer.toString(code));

        verifAlfa = (Button) findViewById(R.id.verifAlfa);
        verifAlfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TicketDetail.class));
            }
        });
    }

    @Override
    public void generate() {
        Random random = new Random();
        code = random.nextInt(9999999);
    }
}