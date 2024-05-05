package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.validator.routines.EmailValidator;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtContrasena;

    private FirebaseAuth fblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtLoginEmail);
        txtContrasena = findViewById(R.id.txtLoginContrasena);
        fblogin = FirebaseAuth.getInstance();
    }

    public void onLoginAcceder(View view) {
        String usuario, contrasena;
        usuario = txtUsuario.getText().toString().trim();
        contrasena = txtContrasena.getText().toString().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!EmailValidator.getInstance().isValid(usuario)) {
            Toast.makeText(this, "No es un email válido", Toast.LENGTH_SHORT).show();
        } else {
            fblogin.signInWithEmailAndPassword(usuario, contrasena).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    MainActivity.usuario = fblogin.getCurrentUser();
                    startActivity(new Intent(this, MainActivity.class));
                    this.finish();
                } else {
                    String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                    switch (error) {
                        case "ERROR_WRONG_PASSWORD":
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            txtUsuario.setText("");
                            txtContrasena.setText("");
                            break;
                        case "ERROR_USER_NOT_FOUND":
                            Toast.makeText(this, "La cuenta no existe", Toast.LENGTH_SHORT).show();
                            txtUsuario.setText("");
                            txtContrasena.setText("");
                            break;
                        default:
                            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                            txtUsuario.setText("");
                            txtContrasena.setText("");
                    }
                }
            });
        }
    }

    public void onLoginRegistrar(View view) {
        startActivity(new Intent(this, RegistrarActivity.class));
        this.finish();
    }
}