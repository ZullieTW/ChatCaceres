package com.example.chatcceres;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
}
