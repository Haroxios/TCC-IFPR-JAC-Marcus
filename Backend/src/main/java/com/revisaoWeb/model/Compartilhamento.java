package com.revisaoWeb.model;

public class Compartilhamento {

    private int idCompartilhamento;
    private Permissao permissao;
    private Usuario usuario;
    private Documento documento;

    public Compartilhamento() {}

    public Compartilhamento(int idCompartilhamento, Usuario usuario, Permissao permissao) {
        this(idCompartilhamento, usuario, permissao, null);
    }

    public Compartilhamento(int idCompartilhamento, Usuario usuario, Permissao permissao, Documento documento) {
        setIdCompartilhamento(idCompartilhamento);
        setUsuario(usuario);
        setPermissao(permissao);
        setDocumento(documento);
    }

    public int getIdCompartilhamento() {
        return idCompartilhamento;
    }

    public void setIdCompartilhamento(int idCompartilhamento) {
        this.idCompartilhamento = idCompartilhamento;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Compartilhamento: " +getIdCompartilhamento() +"\n" +getPermissao() +"\n" +getUsuario() +"\n" +getDocumento();
    }
}
