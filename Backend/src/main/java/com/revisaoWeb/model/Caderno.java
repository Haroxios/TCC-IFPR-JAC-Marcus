package com.revisaoWeb.model;

public class Caderno {

    private int idCaderno;
    private String caderno;
    private Usuario usuario;

    public Caderno() {}

    public Caderno(int idCaderno, String caderno, Usuario usuario) {
        setIdCaderno(idCaderno);
        setCaderno(caderno);
        setUsuario(usuario);
    }

    public int getIdCaderno() {
        return idCaderno;
    }

    public void setIdCaderno(int idCaderno) {
        this.idCaderno = idCaderno;
    }

    public String getCaderno() {
        return caderno;
    }

    public void setCaderno(String caderno) {
        this.caderno = caderno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Caderno: " +getIdCaderno() +"\n" +getCaderno() +"\n" +getUsuario();
    }
}
