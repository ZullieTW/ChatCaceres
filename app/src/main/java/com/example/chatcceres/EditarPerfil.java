package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

    }
    public void onEditRegistrar(View view) {
        //Comprobación entrada de datos





        //Editar perfil
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}