package com.example.chatcceres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.example.chatcceres.adapter.BusquedaUsuarioAdapter;

public class BuscardorActivity extends AppCompatActivity {

    private EditText txtBuscador;
    private RecyclerView listaResultados;

    private BusquedaUsuarioAdapter adapterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscardor);

        txtBuscador = findViewById(R.id.txtBuscador);
        listaResultados = findViewById(R.id.ly_resultados_busqueda);

        txtBuscador.requestFocus();
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

        Query query = FirebaseUtil.usuariosCollectionReference().whereGreaterThanOrEqualTo("email", busqueda);
        FirestoreRecyclerOptions<Usuario> options = new FirestoreRecyclerOptions.Builder<Usuario>().setQuery(query,Usuario.class).build();

        adapterRecyclerView = new BusquedaUsuarioAdapter(options, getApplicationContext());
        listaResultados.setLayoutManager(new LinearLayoutManager(this));
        listaResultados.setAdapter(adapterRecyclerView);
        adapterRecyclerView.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterRecyclerView != null){
            adapterRecyclerView.stopListening();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapterRecyclerView != null){
            adapterRecyclerView.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapterRecyclerView != null){
            adapterRecyclerView.startListening();
        }
    }
}