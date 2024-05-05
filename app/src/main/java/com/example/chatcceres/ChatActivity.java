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

import com.example.chatcceres.modelos.Chat;
import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.AndroidUtil;
import com.example.chatcceres.utils.FirebaseUtil;
import com.google.firebase.Timestamp;

import org.checkerframework.checker.units.qual.C;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    private Chat chat;
    private String chatId;
    private EditText txtInput;
    private Usuario receptor;
    private RecyclerView mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        mensajes = findViewById(R.id.ly_chat_mensajes);
        txtInput = findViewById(R.id.txtInputChat);
        TextView txtReceptor = findViewById(R.id.txtChatUsuario);


        receptor = AndroidUtil.getDatosUsuarioDeIntent(this.getIntent());
        txtReceptor.setText(receptor.getEmail());
        chatId = FirebaseUtil.getChatId(FirebaseUtil.getUid(), receptor.getUid());

        getChat();
    }

    private  void getChat(){
        FirebaseUtil.chatsCollectionReference(chatId).get().addOnCompleteListener(task -> {
            chat = task.getResult().toObject(Chat.class);
            if(chat == null){
                chat = new Chat(
                        chatId,
                        Arrays.asList(FirebaseUtil.getUid(),receptor.getUid()),
                        Timestamp.now(),
                        ""
                );
                FirebaseUtil.chatsCollectionReference(chatId).set(chat);
            }
        });
    }
    public void onBotonAtrasChat(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}