package com.teamworks.gallerytw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    Button signIn;
    private FirebaseAuth xauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.etSIemail);
        password = findViewById(R.id.etSIpassword);
        signIn = findViewById(R.id.btnSI);

        xauth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();

                if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p)){

                    Toast.makeText(SignIn.this, "Please input", Toast.LENGTH_SHORT).show();
                    return;
                }

                xauth.signInWithEmailAndPassword(e, p)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(SignIn.this, MainActivity.class));
                                    finish();
                                } else {

                                    Toast.makeText(SignIn.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }
}