package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.adapter.ChatsRecientesAdapter;
import com.example.chatcceres.adapter.ChatsRecientesAdapter;
import com.example.chatcceres.modelos.Chat;
import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    private RecyclerView chats_recientes;
    private ChatsRecientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        chats_recientes = findViewById(R.id.ly_chat_recientes);


        prepararChats();
    }

    private void prepararChats() {
        Query query = FirebaseUtil.todosChatsCollectionReference()
                .whereArrayContains("idUsuarios", FirebaseUtil.getUid());
        FirestoreRecyclerOptions<Chat> options = new FirestoreRecyclerOptions.Builder<Chat>().setQuery(query, Chat.class).build();


        adapter = new ChatsRecientesAdapter(options,getApplicationContext());
        chats_recientes.setLayoutManager(new LinearLayoutManager(this));
        chats_recientes.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    public void onEditUsuario(View view) {
        startActivity(new Intent(this, EditarPerfil.class));
    }

    public void onAnadirChat(View view) {
        startActivity(new Intent(this, BuscardorActivity.class));
    }
}