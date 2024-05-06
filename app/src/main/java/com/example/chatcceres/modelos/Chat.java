package com.example.chatcceres.modelos;

import com.google.firebase.Timestamp;

import java.util.List;

public class Chat {
    private String id;
    private List<String> idUsuarios;
    private Timestamp momentoUltimoMensaje;
    private String autorIdUltimoMensaje;
    private String ultimoMensaje;


    public Chat() {
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public Chat(String id, List<String> idUsuarios, Timestamp momentoUltimoMensaje, String autorIdUltimoMensaje, String ultimoMensaje) {
        this.id = id;
        this.idUsuarios = idUsuarios;
        this.momentoUltimoMensaje = momentoUltimoMensaje;
        this.autorIdUltimoMensaje = autorIdUltimoMensaje;
        this.ultimoMensaje = ultimoMensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(List<String> idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Timestamp getMomentoUltimoMensaje() {
        return momentoUltimoMensaje;
    }

    public void setMomentoUltimoMensaje(Timestamp momentoUltimoMensaje) {
        this.momentoUltimoMensaje = momentoUltimoMensaje;
    }

    public String getAutorIdUltimoMensaje() {
        return autorIdUltimoMensaje;
    }

    public void setAutorIdUltimoMensaje(String autorIdUltimoMensaje) {
        this.autorIdUltimoMensaje = autorIdUltimoMensaje;
    }
}
