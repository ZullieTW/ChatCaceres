package com.example.chatcceres.modelos;

import com.google.firebase.Timestamp;

public class Usuario {
    private String email;
    private String uid;
    private Timestamp momentoRegistro;

    public Usuario() {
    }

    public Usuario(String email, String uid, Timestamp momentoRegistro) {
        this.email = email;
        this.uid = uid;
        this.momentoRegistro = momentoRegistro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getMomentoRegistro() {
        return momentoRegistro;
    }

    public void setMomentoRegistro(Timestamp momentoRegistro) {
        this.momentoRegistro = momentoRegistro;
    }
}
