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

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email, pass;
    Button button,googleAuth;
    TextView regis;
    private FirebaseAuth mAuth;
    private static final String TAG = "GoogleActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    nampungLogin a = new nampungLogin();
//    GoogleSignInAccount account =  GoogleSignIn.getLastSignedInAccount(getApplicationContext());
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
        googleAuth = (Button) findViewById(R.id.loginGoogle);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(this);
        regis.setOnClickListener(this);
        googleAuth.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
                            a.setEmail(user.getEmail());
                            System.out.println(a.getUID());
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
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
            case R.id.loginGoogle:
                loginAuthentication();
                break;
        }
    }

    private void loginAuthentication() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
//                            a.setEmail(user != null ? user.getEmail() : null);
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
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