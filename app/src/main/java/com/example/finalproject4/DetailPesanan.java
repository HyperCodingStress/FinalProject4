package com.example.finalproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPesanan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    private Spinner spinner;
    private String status;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        spinner = findViewById(R.id.price); // spinner

        //spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pricelist, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        //list item di firebase
        recyclerView = (RecyclerView) findViewById(R.id.testRc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Data").orderByChild("harga"), DataModel.class)
                        .build();
        dataAdapter = new DataAdapter(options);
        recyclerView.setAdapter(dataAdapter);

        //popup
        builder = new AlertDialog.Builder(this);
        dataAdapter.OnRecyclerViewClickListener(new DataAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onClick(int position) {

                builder.setTitle("Pesanan")
                        .setMessage("Apakah anda yakin membeli tiket " + dataAdapter.DataNama + " ini?")
                        .setCancelable(true)
                        .setPositiveButton("yakin", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent =  new Intent(DetailPesanan.this,Seat.class);
                                intent.putExtra("nama",dataAdapter.DataNama);
                                intent.putExtra("type", dataAdapter.DataType);
                                intent.putExtra("rating", dataAdapter.DataRating);
                                intent.putExtra("estimasi", dataAdapter.DataEstimate);
                                intent.putExtra("hargaTiket", dataAdapter.DataHarga);
                                intent.putExtra("rate", dataAdapter.DataRate);
                                intent.putExtra("hours", dataAdapter.DataHours);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataAdapter.startListening();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String pilihan = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),pilihan, Toast.LENGTH_SHORT).show();
        pilihan = status;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}