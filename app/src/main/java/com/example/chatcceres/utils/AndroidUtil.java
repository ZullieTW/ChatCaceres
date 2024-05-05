package com.example.chatcceres.utils;

import android.content.Intent;

import com.example.chatcceres.modelos.Usuario;

public class AndroidUtil {

    public static void pasarDatosUsuarioEnIntent(Intent intent, Usuario usuario){
        intent.putExtra("usuario",usuario.getEmail());
        intent.putExtra("uid",usuario.getUid());
    }
    public static Usuario getDatosUsuarioDeIntent(Intent intent){
        Usuario usuario= new Usuario();
        usuario.setEmail(intent.getStringExtra("usuario"));
        usuario.setUid(intent.getStringExtra("uid"));
        return usuario;
    }
}
