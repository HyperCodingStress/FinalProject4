package com.example.finalproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email, pass;
    Button button;
    TextView regis;
    private FirebaseAuth mAuth;
    private static final String TAG = "MyActivity";
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.Email);
        pass = (EditText) findViewById(R.id.Pass);
        button = (Button) findViewById(R.id.login);
        regis = (TextView) findViewById(R.id.RegisPage);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(this);
        regis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                userlogin();
                break;
            case R.id.RegisPage:
                startActivity(new Intent(this, register.class));
                break;
        }
    }

    private void userlogin() {
        String emails = email.getText().toString().trim();
        String passwords = pass.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
            email.setError("Masukan Email Yang Valid");
            email.requestFocus();
            return;
        }
        if (emails.isEmpty()) {
            email.setError("Email Tidak Boleh Kosong");
            email.requestFocus();
            return;
        }
        if (passwords.isEmpty()) {
            pass.setError("Password Tidak Boleh Kosong");
            pass.requestFocus();
            return;
        }
        if (passwords.length() < 8) {
            pass.setError("Password Tidak Boleh Kurang dari 8");
            pass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), testing.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}