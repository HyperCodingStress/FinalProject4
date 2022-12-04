package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Seat extends AppCompatActivity {
    TextView nama,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        nama = (TextView) findViewById(R.id.NamaDetail);
        type = (TextView) findViewById(R.id.TypeDetail);

        String name = getIntent().getStringExtra("nama");
        String tipe = getIntent().getStringExtra("type");

        nama.setText(name);
        type.setText(tipe);
    }
}