package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.validator.routines.EmailValidator;

public class RegistrarActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtContrasena;
    private EditText txtConfirmacion_contrasena;

    private FirebaseAuth fblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);

        txtUsuario = findViewById(R.id.txtRegUsuario);
        txtContrasena = findViewById(R.id.txtRegContrasena);
        txtConfirmacion_contrasena = findViewById(R.id.txtRegCContrasena);

        fblogin = FirebaseAuth.getInstance();
    }

    public void onRegistroCancelar(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    public void onRegistroRegistrar(View view) {
        String usuario, contrasena, ccontrasena;
        usuario = txtUsuario.getText().toString().trim();
        contrasena = txtContrasena.getText().toString().trim();
        ccontrasena = txtConfirmacion_contrasena.getText().toString().trim();

        if (usuario.isEmpty() || contrasena.isEmpty() || ccontrasena.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!EmailValidator.getInstance().isValid(usuario)) {
            Toast.makeText(this, "No es un email válido", Toast.LENGTH_SHORT).show();
        } else if (!contrasena.equals(ccontrasena)) {
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
        } else {
            fblogin.createUserWithEmailAndPassword(usuario, contrasena).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    MainActivity.usuario = fblogin.getCurrentUser();
                    Toast.makeText(this, "Registrado con éxito", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    this.finish();
                } else {
                    Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    txtContrasena.setText("");
                    txtConfirmacion_contrasena.setText("");
                    txtUsuario.setText("");
                }
            });
        }
    }
}