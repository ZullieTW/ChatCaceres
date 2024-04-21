package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.txtLoginEmail);
        contrasena = findViewById(R.id.txtLoginContrasena);
    }

    public void onLoginAcceder(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    public void onLoginRegistrar(View view) {
        startActivity(new Intent(this, RegistrarActivity.class));
        this.finish();
    }
}