package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DetailPesanan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        //list item di firebase
        recyclerView = (RecyclerView)findViewById(R.id.testRc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Data"),DataModel.class)
                        .build();
        dataAdapter = new DataAdapter(options);
        recyclerView.setAdapter(dataAdapter);

        spinner = findViewById(R.id.price); // spinner
        //spinner
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.pricelist,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}