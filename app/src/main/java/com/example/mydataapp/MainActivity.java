package com.example.mydataapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button tmblLogin, tmblCancel;
    public static final String MyPREFERENCES = "mypreference";
    public static final String isLoggedIn = "isLoggedIn";
    SharedPreferences sharedPreferences;

    String correctUsername = "admin";
    String correctPassword = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        tmblLogin = findViewById(R.id.btnLogin);
        tmblCancel = findViewById(R.id.btnCancel);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        tmblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = etUsername.getText().toString().trim();
                String passwordInput = etPassword.getText().toString().trim();

                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Username dan Password wajib diisi", Toast.LENGTH_SHORT).show();
                }

                else if (usernameInput.equals(correctUsername) && passwordInput.equals(correctPassword)) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(isLoggedIn, true);
                    editor.putString("username", usernameInput);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);

                    finish();
                }
                else {

                    Toast.makeText(MainActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tmblCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etUsername.setText("");
                etPassword.setText("");

                Toast.makeText(MainActivity.this, "Input berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }
}