package com.example.stayinfit;

import static com.example.stayinfit.fbref.refUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNewEmail;
    private EditText etNewUsername;
    private EditText etNewPassword;
    private EditText etNewConfirmPassword;
    private FirebaseAuth mAuth;
    private Button btnSignUp;
    private Button btnBackToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initObjects();
        mAuth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etNewEmail.getText().toString();
                String userName = etNewUsername.getText().toString();
                String password = etNewPassword.getText().toString();
                String confirmPassword = etNewConfirmPassword.getText().toString();
                if(password.equals(confirmPassword)){
                    users t = new users(email,userName, password);
                    refUsers.child(t.getUserName()).setValue(t);
                }
                //Intent i = new Intent(getApplicationContext(), registerActivity.class);
                //startActivity(i);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RegisterActivity.this, "Authentication succeeded.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void initObjects(){
        etNewEmail = findViewById(R.id.editTextNewEmail);
        etNewUsername = findViewById(R.id.editTextNewUsername);
        etNewPassword  = findViewById(R.id.editTextNewPassword);
        etNewConfirmPassword  = findViewById(R.id.editTextConfirmPassword);
        btnSignUp = findViewById(R.id.buttonSignUp);
        btnBackToLogin = findViewById(R.id.buttonSignUpToLogIn);
    }


}