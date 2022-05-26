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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private TextView tv_register;
    private FirebaseAuth mAuth;
    private Button btnSignIn;
    private TextView txtEmailSignIn;
    private TextView txtPasswordSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        tv_register = findViewById(R.id.tv_regisster);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewRegister = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intentViewRegister);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        txtEmailSignIn = findViewById(R.id.tv_email);
        txtPasswordSignIn = findViewById(R.id.tv_password);

        btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAccount(txtEmailSignIn.getText().toString(), txtPasswordSignIn.getText().toString());
            }
        });
    }

    private void loginAccount(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(SignInActivity.this, "Sign in successful",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, ListActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Sign in failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}
