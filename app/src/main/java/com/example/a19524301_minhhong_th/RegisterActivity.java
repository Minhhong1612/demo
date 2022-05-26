package com.example.a19524301_minhhong_th;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private Button btnRegister;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtName;
    private TextView txtViewSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisster);

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        txtName = findViewById(R.id.tv_rigisstername);
        txtEmail = findViewById(R.id.tv_registeremail);
        txtPassword = findViewById(R.id.tv_rigissterpassword);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount(txtName.getText().toString(),
                        txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });

        txtViewSignIn = findViewById(R.id.textViewSignIn);
        txtViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewSignIn = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intentViewSignIn);
            }
        });
    }

    private void registerAccount(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveAccountInfo(name, email);
                            Toast.makeText(RegisterActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    private void saveAccountInfo(String name, String email){
        // Create a new user with a first and last name
        Map<String, Object> account = new HashMap<>();
        account.put("name", name);
        account.put("email", email);

        // Add a new document with a generated ID
        mFireStore.collection("accounts")
                .add(account)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        cleanText();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Register failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void cleanText(){
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
}
