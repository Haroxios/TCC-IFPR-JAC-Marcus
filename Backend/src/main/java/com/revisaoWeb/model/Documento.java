package com.revisaoWeb.model;

import java.sql.Date;

public class Documento {

    private int idDocumento;
    private String titulo;
    private String previa;
    private String conteudo;
    private Date dataCriacao;
    private Date dataUltimaEdicao;
    private Date dataProximaRevisao;
    private int nivelRevisao;
    private Status statusRevisao;
    private Caderno caderno;

    public Documento() {}

    public Documento(int idDocumento, Date dataCriacao, Date dataUltimaEdicao, Date dataProximaRevisao, int nivelRevisao, Caderno caderno) {
        this(idDocumento, null, null, null, dataCriacao, dataUltimaEdicao, dataProximaRevisao, nivelRevisao, null, caderno);
    }

    public Documento(int idDocumento, String titulo, String previa, String conteudo, Date dataCriacao, Date dataUltimaEdicao, Date dataProximaRevisao, int nivelRevisao, Status statusRevisao, Caderno caderno) {
        setIdDocumento(idDocumento);
        setTitulo(titulo);
        setPrevia(previa);
        setConteudo(conteudo);
        setDataCriacao(dataCriacao);
        setDataUltimaEdicao(dataUltimaEdicao);
        setDataProximaRevisao(dataProximaRevisao);
        setNivelRevisao(nivelRevisao);
        setStatusRevisao(statusRevisao);
        setCaderno(caderno);
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrevia() {
        return previa;
    }

    public void setPrevia(String previa) {
        this.previa = previa;
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

    public Date getDataProximaRevisao() {
        return dataProximaRevisao;
    }

    public void setDataProximaRevisao(Date dataProximaRevisao) {
        this.dataProximaRevisao = dataProximaRevisao;
    }

    public int getNivelRevisao() {
        return nivelRevisao;
    }

    public void setNivelRevisao(int nivelRevisao) {
        this.nivelRevisao = nivelRevisao;
    }

    public Status getStatusRevisao () {
        return statusRevisao;
    }

    public void setStatusRevisao (Status statusRevisao) {
        this.statusRevisao = statusRevisao;
    }

    public Caderno getCaderno() {
        return caderno;
    }

    public void setCaderno(Caderno caderno) {
        this.caderno = caderno;
    }

    @Override
    public String toString() {
        return "Documento: " +getIdDocumento() +"\n" +getTitulo() +"\n" +getPrevia() +"\n" +getConteudo() +"\n" +getDataCriacao() +"\n" +getDataUltimaEdicao() +"\n" +getDataProximaRevisao() +"\n" +getNivelRevisao() +"\n" +getStatusRevisao() +"\n" +getCaderno();
    }
}
