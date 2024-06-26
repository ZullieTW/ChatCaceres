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
import com.example.chatcceres.utils.AndroidUtil;
import com.example.chatcceres.utils.FirebaseUtil;
import com.example.chatcceres.R;
import com.example.chatcceres.modelos.Usuario;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class BusquedaUsuarioAdapter extends FirestoreRecyclerAdapter<Usuario, BusquedaUsuarioAdapter.ModeloUsuarioViewHolder> {

    private final Context context;

    public BusquedaUsuarioAdapter(@NonNull FirestoreRecyclerOptions<Usuario> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ModeloUsuarioViewHolder modeloUsuarioViewHolder, int i, @NonNull Usuario usuario) {
        modeloUsuarioViewHolder.txtUsuario.setText(usuario.getEmail());
        modeloUsuarioViewHolder.txtFecha.setText(usuario.getMomentoRegistro().toDate().toString());

        if(usuario.getUid().equals(FirebaseUtil.getUid())){
            String label = "(Yo) " + usuario.getEmail();
            modeloUsuarioViewHolder.txtUsuario.setText(label);
        }

        modeloUsuarioViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            AndroidUtil.pasarDatosUsuarioEnIntent(intent, usuario);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public ModeloUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.busqueda_usuario_fila, parent, false);
        return new ModeloUsuarioViewHolder(vista);
    }

    class ModeloUsuarioViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtUsuario;
        private final TextView txtFecha;

        public ModeloUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUsuario = itemView.findViewById(R.id.txtItemUsuario);
            txtFecha = itemView.findViewById(R.id.txtItemFecha);
        }
    }
}
