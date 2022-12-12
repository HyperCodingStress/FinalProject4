package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class PaymentMethod extends AppCompatActivity implements View.OnClickListener{
    TextView bni,alfa,TotalHarga;
    String tampungHarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        bni = (TextView) findViewById(R.id.BniPayment);
        alfa = (TextView) findViewById(R.id.alfaPayment);
        TotalHarga = (TextView) findViewById(R.id.TotalHarga);
        int hargaTemp = getIntent().getIntExtra("harga",0);
        System.out.println(hargaTemp);
        TotalHarga.setText(String.valueOf("Rp"+hargaTemp));

        bni.setOnClickListener(this);
        alfa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BniPayment:
                Intent intent = new Intent(this,bniPayment.class);
                intent.putExtra("harga",tampungHarga);
                startActivity(intent);
                break;
            case R.id.alfaPayment:
                Intent Intent = new Intent(this,alfaPayment.class);
                Intent.putExtra("harga",tampungHarga);
                startActivity(Intent);
                break;
        }
    }
}