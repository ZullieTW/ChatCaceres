package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatcceres.utils.FirebaseUtil;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    static public FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuario = FirebaseUtil.getUsuario();
    }

    public void onEditUsuario(View view) {
        startActivity(new Intent(this, EditarPerfil.class));
    }

    public void onAnadirChat(View view) {
        startActivity(new Intent(this, BuscardorActivity.class));
    }
}