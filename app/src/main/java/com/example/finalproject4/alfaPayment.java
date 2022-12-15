package com.example.finalproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
                final Dialog dialog = new Dialog(alfaPayment.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_qrscan);

                final ImageView mImage = dialog.findViewById(R.id.qr);

                QRCodeWriter qrgenerator = new QRCodeWriter();

                try {
                    BitMatrix matrix = qrgenerator.encode(Integer.toString(code), BarcodeFormat.QR_CODE,500,500);
                    Bitmap bitmap = Bitmap.createBitmap(500,500,Bitmap.Config.RGB_565);

                    for (int i = 0; i < 500; i++){
                        for (int j = 0; j < 500; j++){
                            bitmap.setPixel(i,j,matrix.get(i,j)? Color.BLACK:Color.WHITE);
                        }
                    }
                    mImage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                dialog.show();
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
                                Intent intent = new Intent(alfaPayment.this,succesPayment.class);
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
                                intent.putExtra("via","ALFAMART");
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