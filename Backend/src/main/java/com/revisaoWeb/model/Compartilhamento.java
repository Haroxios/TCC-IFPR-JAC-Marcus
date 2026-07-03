package com.revisaoWeb.model;

public class Compartilhamento {

    private int id;
    private Usuario usuario;
    private Permissao permissao;
    private Documento documento;    //pode ser null
    private Caderno caderno;        //pode ser null

    public Compartilhamento() {}

    public Compartilhamento(int id, Usuario usuario, Permissao permissao) {
        this(id, usuario, permissao, null, null);
    }

    public Compartilhamento(int id, Usuario usuario, Permissao permissao, Documento documento, Caderno caderno) {
        setId(id);
        setUsuario(usuario);
        setPermissao(permissao);
        setDocumento(documento);
        setCaderno(caderno);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private Permissao getPermissao() {
        return permissao;
    }

    private void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    private Documento getDocumento() {
        return documento;
    }

    private void setDocumento(Documento documento) {
        this.documento = documento;
    }

    private Caderno getCaderno() {
        return caderno;
    }

    private void setCaderno(Caderno caderno) {
        this.caderno = caderno;
    }

    @Override
    public String toString() {
        return "Compartilhmento: " +getId() +"\n" +getUsuario() +"\n" +getPermissao() +"\n" +getDocumento() +"\n" +getCaderno();
    }
}
