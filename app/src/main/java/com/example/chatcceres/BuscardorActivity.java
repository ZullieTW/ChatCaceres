package com.example.chatcceres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class BuscardorActivity extends AppCompatActivity {

    private EditText txtBuscador;
    private RecyclerView listaResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscardor);

        txtBuscador = findViewById(R.id.txtBuscador);
        listaResultados = findViewById(R.id.ly_resultados_busqueda);
    }

    public void onBotonAtras(View view) {
        this.finish();
    }

    public void onBuscar(View view) {
        String busqueda = txtBuscador.getText().toString();

        if (busqueda.isEmpty()){
            Toast.makeText(this, "Campo de búsqueda vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        prepararResultados(busqueda);

    }
    private void prepararResultados(String busqueda){

    }
}