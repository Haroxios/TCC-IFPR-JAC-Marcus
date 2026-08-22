package com.revisaoWeb.model;

import java.sql.Date;

public class Documento {

    private int id;
    private String titulo;
    private String conteudo;
    private Date dataCriacao;
    private Date dataUltimaEdicao;
    private Status statusRevisao;
    private Date proximaRevisao;
    private Caderno caderno;

    public Documento() {}

    public Documento(int id, Date dataCriacao, Date dataUltimaEdicao, Date proximaRevisao, Caderno caderno) {
        this(id, null, null, dataCriacao, dataUltimaEdicao, null, proximaRevisao, caderno);
    }

    public Documento(int id, String titulo, String conteudo, Date dataCriacao, Date dataUltimaEdicao, Status statusRevisao, Date proximaRevisao, Caderno caderno) {
        setId(id);
        setTitulo(titulo);
        setConteudo(conteudo);
        setDataCriacao(dataCriacao);
        setDataUltimaEdicao(dataUltimaEdicao);
        setStatusRevisao(statusRevisao);
        setProximaRevisao(proximaRevisao);
        setCaderno(caderno);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaEdicao() {
        return dataUltimaEdicao;
    }

    public void setDataUltimaEdicao(Date dataUltimaEdicao) {
        this.dataUltimaEdicao = dataUltimaEdicao;
    }

    public Status getStatusRevisao () {
        return statusRevisao;
    }

    public void setStatusRevisao (Status statusRevisao) {
        this.statusRevisao = statusRevisao;
    }

    public Date getProximaRevisao() {
        return proximaRevisao;
    }

    public void setProximaRevisao(Date proximaRevisao) {
        this.proximaRevisao= proximaRevisao;
    }

    public Caderno getCaderno() {
        return caderno;
    }

    public void setCaderno(Caderno caderno) {
        this.caderno = caderno;
    }

    @Override
    public String toString() {
        return "Documento: " +getId() +"\n" +getTitulo() +"\n" +getConteudo() +"\n" +getDataCriacao() +"\n" +getDataUltimaEdicao() +"\n" +getStatusRevisao() +"\n" +getProximaRevisao() +"\n" +getCaderno();
    }
}
