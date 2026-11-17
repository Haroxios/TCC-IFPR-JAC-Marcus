package com.revisaoWeb.dto;

public class LoginRespostaDto {
    private int idUsuario;
    private String usuario;
    private String email;
    private String token;

    public LoginRespostaDto(int idUsuario, String usuario, String email, String token) {
        setIdUsuario(idUsuario);
        setUsuario(usuario);
        setEmail(email);
        setToken(token);
    }

    public LoginRespostaDto() {

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
