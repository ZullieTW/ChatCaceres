package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.chatcceres.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.validator.routines.EmailValidator;

public class EditarPerfil extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtContrasenaVieja;
    private EditText txtContrasenaNueva;
    private EditText txtCContrasenaNueva;

    private FirebaseUser usuarioRegistrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

        txtUsuario = findViewById(R.id.txtEditUsuario);
        txtUsuario.setText(FirebaseUtil.getEmail());
        txtUsuario.setFocusable(false);

        txtContrasenaVieja = findViewById(R.id.txtEditContrasenaActual);
        txtContrasenaNueva = findViewById(R.id.txtEditContrasenaNueva);
        txtCContrasenaNueva = findViewById(R.id.txtEditCContrasenaNueva);

        usuarioRegistrado = FirebaseUtil.getUsuario();
    }

    public void onEditGuardar(View view) {
        String contrasena_nueva, contrasena_vieja, ccontrasena_nueva;
        contrasena_vieja = txtContrasenaVieja.getText().toString().trim();
        contrasena_nueva = txtContrasenaNueva.getText().toString().trim();
        ccontrasena_nueva = txtCContrasenaNueva.getText().toString().trim();

        if (contrasena_nueva.isEmpty() || contrasena_vieja.isEmpty() || ccontrasena_nueva.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!contrasena_nueva.equals(ccontrasena_nueva)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            try{
                AuthCredential credencial = EmailAuthProvider.getCredential(FirebaseUtil.getEmail(), contrasena_vieja);
                FirebaseUtil.getUsuario().reauthenticate(credencial).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        usuarioRegistrado.updatePassword(contrasena_nueva);
                    }
                });
                
                Toast.makeText(this, "Datos nuevos guardados", Toast.LENGTH_SHORT).show();
                FirebaseUtil.getAuth().signOut();
                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                ActivityCompat.finishAffinity(this);
            } catch (Exception e){
                Toast.makeText(this, "Error al guardar datos nuevos", Toast.LENGTH_SHORT).show();
                txtUsuario.setText("");
                txtContrasenaVieja.setText("");
                txtContrasenaNueva.setText("");
                txtCContrasenaNueva.setText("");
            }

        }
    }

    public void onEditCancelar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        this.finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        this.finish();
    }

    public void onCerrarSesion(View view) {
        FirebaseUtil.getAuth().signOut();
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        ActivityCompat.finishAffinity(this);
    }
}