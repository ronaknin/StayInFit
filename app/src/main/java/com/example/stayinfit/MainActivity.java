package com.example.stayinfit;
import android.content.SharedPreferences;

import static com.example.stayinfit.fbref.refUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogIn;
    private Button btnNewAccount;
    private EditText etEmail;
    private EditText etPassword;
    private FirebaseAuth mAuth;
    private CheckBox cbrem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        initObjects();
        /*
        SharedPreferences sharedPreferencess = getSharedPreferences("UserDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencess.edit();
        editor.remove("RememberMe");
        editor.remove("Email");
        editor.remove("Password");
        editor.apply();*/
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("RememberMe", false))
        {
            String email = sharedPreferences.getString("Email", "NULL");
            String password = sharedPreferences.getString("Password", "NULL");
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                            Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
                                    if (cbrem.isChecked() && !sharedPreferences.getBoolean("RememberMe", false))
                                    {
                                        String email = etEmail.getText().toString();
                                        String password = etPassword.getText().toString();
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("RememberMe", true);
                                        editor.putString("Email",email);
                                        editor.putString("Password",password);
                                        editor.commit();
                                    }
                                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    public void initObjects(){
        btnNewAccount = findViewById(R.id.buttonLoginToSignUp);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogIn = findViewById(R.id.buttonLogin);
        cbrem = findViewById(R.id.rememberMeCheckBox);
    }
}