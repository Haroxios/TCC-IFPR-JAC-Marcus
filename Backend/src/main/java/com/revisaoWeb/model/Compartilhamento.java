package com.revisaoWeb.model;

public class Compartilhamento {

    private int id;
    private Usuario usuario;
    private Permissao permissao;
    private Documento documento;

    public Compartilhamento() {}

    public Compartilhamento(int id, Usuario usuario, Permissao permissao) {
        this(id, usuario, permissao, null);
    }

    public Compartilhamento(int id, Usuario usuario, Permissao permissao, Documento documento) {
        setId(id);
        setUsuario(usuario);
        setPermissao(permissao);
        setDocumento(documento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Compartilhmento: " +getId() +"\n" +getPermissao() +"\n" +getUsuario() +"\n" +getDocumento();
    }
}
