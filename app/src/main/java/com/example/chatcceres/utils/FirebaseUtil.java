package com.example.chatcceres.utils;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    public static FirebaseAuth getAuth(){
        return FirebaseAuth.getInstance();
    }
    public static FirebaseUser getUsuario(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public static String getUid(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static String getEmail(){
        try{
            return FirebaseAuth.getInstance().getCurrentUser().getEmail();
        } catch (NullPointerException e){
            return "USUARIO_NO_ENCONTRADO";
        }
    }
    public static DocumentReference detallesUsuario(){
        return FirebaseFirestore.getInstance().collection("usuarios").document(getUid());
    }

    public static DocumentReference chatsCollectionReference(String chatId){
        return FirebaseFirestore.getInstance().collection("chats").document(chatId);
    }

    public static CollectionReference usuariosCollectionReference(){
        return FirebaseFirestore.getInstance().collection("usuarios");
    }

    public static String getChatId(String usuario1Id, String usuario2Id){
        if (usuario1Id.hashCode()<usuario2Id.hashCode()){
            return usuario1Id+"_"+usuario2Id;
        } else {
            return usuario2Id+"_"+usuario1Id;
        }
    }
}
