package com.example.chatcceres.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.ChatActivity;
import com.example.chatcceres.utils.AndroidUtil;
import com.example.chatcceres.utils.FirebaseUtil;
import com.example.chatcceres.R;
import com.example.chatcceres.modelos.MensajeChat;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatAdapter extends FirestoreRecyclerAdapter<MensajeChat, ChatAdapter.ModeloChatViewHolder> {

    private final Context context;

    public ChatAdapter(@NonNull FirestoreRecyclerOptions<MensajeChat> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ModeloChatViewHolder modeloChatViewHolder, int i, @NonNull MensajeChat mensajeChat) {
        if(mensajeChat.getAutor().equals(FirebaseUtil.getUid())){
            modeloChatViewHolder.layoutMensajeIzquierda.setVisibility(View.GONE);
            modeloChatViewHolder.layoutMensajeDerecha.setVisibility(View.VISIBLE);

            modeloChatViewHolder.mensajeDerecha.setText(mensajeChat.getMensaje());
        } else {
            modeloChatViewHolder.layoutMensajeIzquierda.setVisibility(View.VISIBLE);
            modeloChatViewHolder.layoutMensajeDerecha.setVisibility(View.GONE);

            modeloChatViewHolder.mensajeIzquierda.setText(mensajeChat.getMensaje());
        }
    }

    @NonNull
    @Override
    public ModeloChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.mensaje_chat_fila, parent, false);
        return new ModeloChatViewHolder(vista);
    }

    class ModeloChatViewHolder extends RecyclerView.ViewHolder {

       private LinearLayout layoutMensajeIzquierda,layoutMensajeDerecha;
       private TextView mensajeIzquierda, mensajeDerecha;

        public ModeloChatViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutMensajeIzquierda = itemView.findViewById(R.id.ly_mensaje_chat_izquierda);
            layoutMensajeDerecha = itemView.findViewById(R.id.ly_mensaje_chat_derecha);
            mensajeIzquierda = itemView.findViewById(R.id.txtMensajeIzquierda);
            mensajeDerecha = itemView.findViewById(R.id.txtMensajeDerecha);

        }
    }
}
