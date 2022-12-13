package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class TicketDetail extends AppCompatActivity {
    RecyclerView recyclerView;
    listAdapter listadapter;
    FirebaseDatabase firebaseDatabase;
    String uid;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        listadapter.startListening();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            uid = currentUser.getUid();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        listadapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        recyclerView = (RecyclerView) findViewById(R.id.listOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("struk").child(uid), DataModel.class)
                        .build();
        listadapter = new listAdapter(options);
        recyclerView.setAdapter(listadapter);

    }
}