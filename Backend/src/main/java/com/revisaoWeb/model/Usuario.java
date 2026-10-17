package com.revisaoWeb.model;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(int idUsuario, String usuario, String email, String senha) {
        setIdUsuario(idUsuario);
        setUsuario(usuario);
        setEmail(email);
        setSenha(senha);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        return "Usuario: " +getIdUsuario() +"\n" +getUsuario() +"\n" +getEmail() +"\n" +getSenha();
    }

}
