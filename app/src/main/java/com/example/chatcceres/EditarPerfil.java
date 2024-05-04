package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.validator.routines.EmailValidator;

public class EditarPerfil extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtContrasena;
    private EditText txtConfirmacion_contrasena;

    private FirebaseUser usuarioRegistrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

        txtUsuario = findViewById(R.id.txtEditUsuario);
        txtContrasena = findViewById(R.id.txtEditContrasena);
        txtConfirmacion_contrasena = findViewById(R.id.txtEditCContrasena);

        usuarioRegistrado = FirebaseUtil.getUsuario();
    }

    public void onEditGuardar(View view) {
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
            try{
                usuarioRegistrado.updatePassword(contrasena);
                usuarioRegistrado.verifyBeforeUpdateEmail(usuario);
                Toast.makeText(this, "Datos nuevos guardados", Toast.LENGTH_SHORT).show();
                this.finish();
            } catch (Exception e){
                Toast.makeText(this, "Error al guardar datos nuevos", Toast.LENGTH_SHORT).show();
                txtUsuario.setText("");
                txtContrasena.setText("");
                txtConfirmacion_contrasena.setText("");
            }

        }
    }

    public void onEditCancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    public void onCerrarSesion(View view) {
        FirebaseUtil.getAuth().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }
}