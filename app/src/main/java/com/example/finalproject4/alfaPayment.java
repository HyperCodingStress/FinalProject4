package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class alfaPayment extends AppCompatActivity implements QRGenerator {
    TextView TotalHarga,numericgenAlfa;
    int code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfa_payment);

        TotalHarga = (TextView) findViewById(R.id.TotalHargaAlfa);
        numericgenAlfa = (TextView) findViewById(R.id.numericgenAlfa);

        TotalHarga.setText(getIntent().getStringExtra("harga"));
        generate();
        numericgenAlfa.setText(Integer.toString(code));
    }

    @Override
    public void generate() {
        Random random = new Random();
        code = random.nextInt(9999999);
    }
}