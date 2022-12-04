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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class register extends AppCompatActivity implements View.OnClickListener{
    EditText email,pass,cpass;
    Button button,RAuth;
    TextView loginPage;
    private FirebaseAuth mAuth;
    private static final String TAG = "MyActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.RegisterEmail);
        pass = (EditText) findViewById(R.id.RegisterPass);
        cpass = (EditText) findViewById(R.id.PassMatch);
        button = (Button) findViewById(R.id.RegisterAcc);
        RAuth = (Button) findViewById(R.id.RegisAuth);
        loginPage = (TextView) findViewById(R.id.LoginPage);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(this);
        RAuth.setOnClickListener(this);
        loginPage.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
            case R.id.RegisAuth:
                regisAuth();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user);
                            startActivity(new Intent(getApplicationContext(), testing.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    private void regisAuth() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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