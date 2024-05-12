package com.example.chatcceres;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.adapter.BusquedaUsuarioAdapter;
import com.example.chatcceres.adapter.ChatAdapter;
import com.example.chatcceres.modelos.Chat;
import com.example.chatcceres.modelos.MensajeChat;
import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.AndroidUtil;
import com.example.chatcceres.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Query;

import org.checkerframework.checker.units.qual.C;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    private ImageButton btnEnviar;
    private Chat chat;
    private String chatId;
    private EditText txtInput;
    private Usuario receptor;
    private RecyclerView mensajes;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        receptor = AndroidUtil.getDatosUsuarioDeIntent(getIntent());
        chatId = FirebaseUtil.getChatId(FirebaseUtil.getUid(), receptor.getUid());

        btnEnviar = findViewById(R.id.btnEnviar);
        mensajes = findViewById(R.id.ly_chat_mensajes);
        txtInput = findViewById(R.id.txtInputChat);
        TextView txtReceptor = findViewById(R.id.txtChatUsuario);


        txtReceptor.setText(receptor.getEmail());

        getChat();
        prepararVistaMensajes();
    }

    private  void getChat(){
        FirebaseUtil.getChatsCollectionReference(chatId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chat = task.getResult().toObject(Chat.class);
                if(chat==null){
                    //first time chat
                    chat = new Chat(
                            chatId,
                            Arrays.asList(FirebaseUtil.getUid(),receptor.getUid()),
                            Timestamp.now(),
                            FirebaseUtil.getUid(),
                            ""
                    );
                    FirebaseUtil.getChatsCollectionReference(chatId).set(chat);
                }
            }
        });
    }
    public void onBotonAtrasChat(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onEnviarMensaje(View view) {
        btnEnviar.setClickable(false);
        String mensaje = txtInput.getText().toString().trim();
        if (!mensaje.isEmpty()){
            enviarMensaje(mensaje);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnEnviar.setClickable(true);
            }
        }, 700);

    }
    private void enviarMensaje(String mensaje){
        chat.setMomentoUltimoMensaje(Timestamp.now());
        chat.setUltimoMensaje(mensaje);
        chat.setAutorIdUltimoMensaje(FirebaseUtil.getUid());
        FirebaseUtil.getChatsCollectionReference(chatId).set(chat);


        MensajeChat mensajeChat = new MensajeChat(mensaje, FirebaseUtil.getUid(), Timestamp.now());
        FirebaseUtil.getMensajeChatCollectionReference(chatId).add(mensajeChat).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                txtInput.setText("");
            }
        });

    }
    private void prepararVistaMensajes(){
        Query query = FirebaseUtil.getMensajeChatCollectionReference(chatId).orderBy("hora", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<MensajeChat> options = new FirestoreRecyclerOptions.Builder<MensajeChat>().setQuery(query,MensajeChat.class).build();

        adapter = new ChatAdapter(options, getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        mensajes.setLayoutManager(manager);
        mensajes.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null){
            adapter.stopListening();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter != null){
            adapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.startListening();
        }
    }
}