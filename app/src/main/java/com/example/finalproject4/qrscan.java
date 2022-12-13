package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class qrscan extends AppCompatActivity {
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        mImage = findViewById(R.id.qr);

        QRCodeWriter qrgenerator = new QRCodeWriter();

        try {
            BitMatrix matrix = qrgenerator.encode(getIntent().getStringExtra("code"), BarcodeFormat.QR_CODE,500,500);
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
    }
}