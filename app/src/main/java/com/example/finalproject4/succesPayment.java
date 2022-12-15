package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class succesPayment extends AppCompatActivity {
    TextView nameDetail,noDetail,busDetail,jamAwalDetail,
            jamAkhirDetail,namaAwalDetail,namaAkhirDetail,strukDetail,metodePayment;

    Button btncontinue,ratebtn;
    RatingBar rating;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_payment);

        nameDetail = (TextView) findViewById(R.id.nameDetail);
        noDetail = (TextView) findViewById(R.id.noDetail);
        busDetail = (TextView) findViewById(R.id.busDetail);
        jamAwalDetail = (TextView) findViewById(R.id.jamAwalDetail);
        jamAkhirDetail = (TextView) findViewById(R.id.jamAkhirDetail);
        namaAwalDetail = (TextView) findViewById(R.id.namaAwalDetail);
        namaAkhirDetail = (TextView) findViewById(R.id.namaAkhirDetail);
        strukDetail = (TextView) findViewById(R.id.strukDetail);
        metodePayment = (TextView) findViewById(R.id.metodePayment);

        int harga = getIntent().getIntExtra("harga",0);
        String namaUser = getIntent().getStringExtra("nama");
        String genderUser = getIntent().getStringExtra("gender");
        String nomorUser = getIntent().getStringExtra("nomor");
        String jamAwal = getIntent().getStringExtra("jamAwal");
        String jamAkhir = getIntent().getStringExtra("jamAwal");
        String terminalAwal = getIntent().getStringExtra("namaAwal");
        String terminalAkhir = getIntent().getStringExtra("namaAkhir");
        String NamaBus = getIntent().getStringExtra("namabis");

        nameDetail.setText(namaUser);
        noDetail.setText(nomorUser);
        strukDetail.setText("Rp"+String.valueOf(harga));
        busDetail.setText(NamaBus);
        jamAwalDetail.setText(jamAwal);
        jamAkhirDetail.setText(jamAkhir);
        namaAwalDetail.setText(terminalAwal);
        namaAkhirDetail.setText(terminalAkhir);
        metodePayment.setText(getIntent().getStringExtra("via"));
        ratebtn = findViewById(R.id.ratebtn);

        ratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });


    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(succesPayment.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.ratingpop);

        final RatingBar rating = dialog.findViewById(R.id.ratingBar);
        final Button next = dialog.findViewById(R.id.ratee);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(succesPayment.this,TicketDetail.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}