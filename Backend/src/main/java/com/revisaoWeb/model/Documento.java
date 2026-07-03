package com.revisaoWeb.model;

import java.util.Date;

public class Documento {

    private int id;
    private String conteudo;
    private Date dataCriacao;
    private Date dataUltimaEdicao;
    private Status statusRevisao;
    private Date proximaRevisao;
    private Caderno caderno;

    public Documento() {}

    public Documento(int id, Date dataCriacao, Date dataUltimaEdicao, Date proximaRevisao, Caderno caderno) {
        this(id, null, dataCriacao, dataUltimaEdicao, null, proximaRevisao, caderno);
    }

    public Documento(int id, String conteudo, Date dataCriacao, Date dataUltimaEdicao, Status statusRevisao, Date proximaRevisao, Caderno caderno) {
        setId(id);
        setConteudo(conteudo);
        setDataCriacao(dataCriacao);
        setDataUltimaEdicao(dataUltimaEdicao);
        setStatusRevisao(statusRevisao);
        setProximaRevisao(proximaRevisao);
        setCadertno(caderno);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getConteudo() {
        return conteudo;
    }

    private void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    private Date getDataCriacao() {
        return dataCriacao;
    }

    private void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    private Date getDataUltimaEdicao() {
        return dataUltimaEdicao;
    }

    private void setDataUltimaEdicao(Date dataUltimaEdicao) {
        this.dataUltimaEdicao = dataUltimaEdicao;
    }

    private Status getStatusRevisao () {
        return statusRevisao;
    }

    private void setStatusRevisao (Status statusRevisao) {
        this.statusRevisao = statusRevisao;
    }

    private Date getProximaRevisao() {
        return proximaRevisao;
    }

    private void setProximaRevisao(Date proximaRevisao) {
        this.proximaRevisao= proximaRevisao;
    }

    private Caderno getCaderno() {
        return caderno;
    }

    private void setCadertno(Caderno caderno) {
        this.caderno = caderno;
    }

    @Override
    public String toString() {
        return "Documento: " +getId() +"\n" +getConteudo() +"\n" +getDataCriacao() +"\n" +getDataUltimaEdicao() +"\n" +getStatusRevisao() +"\n" +getProximaRevisao() +"\n" +getCaderno();
    }
}
