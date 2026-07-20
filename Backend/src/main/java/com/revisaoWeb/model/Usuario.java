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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario: " +getId() +"\n" +getNome() +"\n" +getEmail() +"\n" +getSenha();
    }

}
