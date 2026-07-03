package com.revisaoWeb.model;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setSenha(senha);
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

    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario: " +getId() +"\n" +getNome() +"\n" +getEmail() +"\n" +getSenha();
    }

}
