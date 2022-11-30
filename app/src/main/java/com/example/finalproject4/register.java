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

public class register extends AppCompatActivity implements View.OnClickListener{
    EditText email,pass,cpass;
    Button button;
    TextView loginPage;
    private FirebaseAuth mAuth;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.RegisterEmail);
        pass = (EditText) findViewById(R.id.RegisterPass);
        cpass = (EditText) findViewById(R.id.PassMatch);
        button = (Button) findViewById(R.id.RegisterAcc);
        loginPage = (TextView) findViewById(R.id.LoginPage);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(this);
        loginPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegisterAcc:
                Regis();
                break;
            case R.id.LoginPage:
                startActivity(new Intent(this,login.class));
                break;
        }
    }

    private void Regis() {
        String emails = email.getText().toString().trim();
        String passwords = pass.getText().toString().trim();
        String cpasswords = cpass.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("Masukan Email Yang Valid");
            email.requestFocus();
            return;
        }
        if(emails.isEmpty()){
            email.setError("Email Tidak Boleh Kosong");
            email.requestFocus();
            return;
        }
        if(passwords.isEmpty()){
            pass.setError("Password Tidak Boleh Kosong");
            pass.requestFocus();
            return;
        }
        if(!passwords.equals(cpasswords)){
            cpass.setError("Password Tidak Boleh Beda");
            cpass.requestFocus();
            return;
        }
        if(passwords.length() < 8){
            pass.setError("Password Tidak Boleh Kurang dari 8");
            pass.requestFocus();
            return;
        }
        if(cpasswords.length() < 8){
            cpass.setError("Password Tidak Boleh Kurang dari 8");
            cpass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            reload();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(),login.class));
    }

}