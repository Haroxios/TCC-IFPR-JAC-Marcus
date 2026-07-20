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

    public Caderno getCaderno() {
        return caderno;
    }

    public void setCaderno(Caderno caderno) {
        this.caderno = caderno;
    }

    @Override
    public String toString() {
        return "Compartilhmento: " +getId() +"\n" +getUsuario() +"\n" +getPermissao() +"\n" +getDocumento() +"\n" +getCaderno();
    }
}
