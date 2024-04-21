package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;
    private EditText confirmacion_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);

        usuario = findViewById(R.id.txtRegApodo);
        contrasena = findViewById(R.id.txtRegContrasena);
        confirmacion_contrasena = findViewById(R.id.txtRegCContrasena);


    }

    public void onRegistroCancelar(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    public void onRegistroRegistrar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}