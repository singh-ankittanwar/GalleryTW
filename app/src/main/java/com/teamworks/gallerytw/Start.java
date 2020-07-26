package com.teamworks.gallerytw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start extends AppCompatActivity {

    private Button tsi,tsu;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent x = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(x);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();

        tsi = findViewById(R.id.toSignIn);
        tsu = findViewById(R.id.toSignUp);

        tsi.setOnClickListener(v -> {
            startActivity(new Intent(Start.this, SignIn.class));
            finish();
        });

        tsu.setOnClickListener(v -> {
            startActivity(new Intent(Start.this, SignUp.class));
            finish();
        });
    }
}