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

public class SignUp extends AppCompatActivity {

    EditText email, name, pass, cPass;
    Button signUp;
    private FirebaseAuth xauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.etSUemail);
        name = findViewById(R.id.etSUname);
        pass = findViewById(R.id.etSUpassword);
        cPass = findViewById(R.id.etSUConfirmpass);
        signUp = findViewById(R.id.btnSU);

        xauth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = email.getText().toString().trim();
                String n = name.getText().toString().trim();
                String p = pass.getText().toString().trim();
                String cp = cPass.getText().toString().trim();

                if (TextUtils.isEmpty(e) || TextUtils.isEmpty(n) || TextUtils.isEmpty(p)) {

                    Toast.makeText(SignUp.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!p.equals(cp)) {

                    Toast.makeText(SignUp.this, "Password Unmatch", Toast.LENGTH_SHORT).show();
                    return;
                }

                xauth.createUserWithEmailAndPassword(e, p)
                        .addOnCompleteListener
                                (
                                        SignUp.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    startActivity(new Intent(SignUp.this, SignIn.class));
                                                    Toast.makeText(SignUp.this, "Registration complete", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(SignUp.this, "Auth failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                );
            }

        });


    }
}