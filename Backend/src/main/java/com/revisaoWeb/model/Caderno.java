package com.revisaoWeb.model;

public class Caderno {

    private int id;
    private String nome;
    private Usuario usuario;

    public Caderno() {}

    public Caderno(int id, String nome, Usuario usuario) {
        setId(id);
        setNome(nome);
        setUsuario(usuario);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Caderno: " +getId() +"\n" +getNome() +"\n" +getUsuario();
    }
}
