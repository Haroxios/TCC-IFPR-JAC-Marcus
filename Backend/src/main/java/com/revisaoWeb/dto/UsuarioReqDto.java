package com.revisaoWeb.dto;

import com.revisaoWeb.model.Usuario;

public class UsuarioReqDto {

    private int idUsuario;
    private String usuario;
    private String email;

    public UsuarioReqDto(Usuario usuario) {
        setIdUsuario(usuario.getIdUsuario());
        setUsuario(usuario.getUsuario());
        setEmail(usuario.getEmail());
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
}
