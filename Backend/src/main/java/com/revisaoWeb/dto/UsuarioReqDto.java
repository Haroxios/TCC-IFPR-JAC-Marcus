package com.revisaoWeb.dto;

import com.revisaoWeb.model.Usuario;

public class UsuarioReqDto {

    private int id;
    private String nome;
    private String email;

    public UsuarioReqDto(Usuario usuario) {
        setId(usuario.getId());
        setNome(usuario.getNome());
        setEmail(usuario.getEmail());
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
}
