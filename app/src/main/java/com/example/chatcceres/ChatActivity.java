package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.AndroidUtil;

public class ChatActivity extends AppCompatActivity {

    private EditText txtInput;
    private Usuario receptor;
    private RecyclerView mensajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        txtInput = findViewById(R.id.txtInputChat);
        receptor = AndroidUtil.getDatosUsuarioDeIntent(this.getIntent());

        TextView txtReceptor = findViewById(R.id.txtChatUsuario);
        txtReceptor.setText(receptor.getEmail());

        mensajes = findViewById(R.id.ly_chat_mensajes);
    }

    public void onBotonAtrasChat(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}