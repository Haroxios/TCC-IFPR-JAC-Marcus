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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Caderno: " +getId() +"\n" +getNome() +"\n" +getUsuario();
    }
}
