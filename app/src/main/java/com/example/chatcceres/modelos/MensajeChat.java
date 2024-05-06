package com.example.chatcceres.modelos;

import com.google.firebase.Timestamp;

public class MensajeChat {
    private String mensaje;
    private String autor;
    private Timestamp hora;

    public MensajeChat() {
    }

    public MensajeChat(String mensaje, String autor, Timestamp hora) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }
}
