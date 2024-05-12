package com.example.chatcceres.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatcceres.ChatActivity;
import com.example.chatcceres.modelos.Usuario;
import com.example.chatcceres.utils.AndroidUtil;
import com.example.chatcceres.utils.FirebaseUtil;
import com.example.chatcceres.R;
import com.example.chatcceres.modelos.Chat;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatsRecientesAdapter extends FirestoreRecyclerAdapter<Chat, ChatsRecientesAdapter.ModeloChatViewHolder> {

    private final Context context;

    public ChatsRecientesAdapter(@NonNull FirestoreRecyclerOptions<Chat> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ModeloChatViewHolder modeloChatViewHolder, int i, @NonNull Chat chat) {
        FirebaseUtil.getOtroUsuarioId(chat.getIdUsuarios()).get().addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               Usuario otroUsuario = task.getResult().toObject(Usuario.class);
               modeloChatViewHolder.txtUsuario.setText(otroUsuario.getEmail());
               modeloChatViewHolder.txtMensaje.setText(chat.getUltimoMensaje());

               modeloChatViewHolder.itemView.setOnClickListener(view -> {
                   Intent intent = new Intent(context, ChatActivity.class);
                   AndroidUtil.pasarDatosUsuarioEnIntent(intent, otroUsuario);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent);
               });
           }
        });
    }

    @NonNull
    @Override
    public ModeloChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.chats_recientes_fila, parent, false);
        return new ModeloChatViewHolder(vista);
    }

    class ModeloChatViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtUsuario;
        private final TextView txtMensaje;

        public ModeloChatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUsuario = itemView.findViewById(R.id.txtItemUsuario_chatsRecientes);
            txtMensaje = itemView.findViewById(R.id.txtItemMensaje_chatsRecientes);
        }
    }
}
