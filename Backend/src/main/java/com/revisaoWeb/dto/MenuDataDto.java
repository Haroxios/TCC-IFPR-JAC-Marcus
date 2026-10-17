package com.revisaoWeb.dto;

import com.revisaoWeb.model.Status;
import com.revisaoWeb.model.Permissao;

import java.util.Date;

public class MenuDataDto {

    //usuario
    private int idUsuario;
    private String usuario;

    //tags
    private int idTag;
    private String tag;

    //cadernos
    private int idCaderno;
    private String caderno;
    private int idUsuarioFKCad;

    //documentos
    private int idDocumento;
    private String titulo;
    private String previa;
    private Date dataUltimaEdicao;
    private Date dataProximaRevisao;
    private int nivelRevisao;
    private Status statusRevisao;
    private int idCadernoFKDoc;

    //compartilhamentos
    private int idCompartilhamento;
    private Permissao permissao;
    private int idUsuarioFKCom;
    private int idDocumentoFKCom;

    //tag_doc
    private int idTagDoc;
    private int idDocumentoFKTD;
    private int idTagFKTD;

    public MenuDataDto() {};

    public MenuDataDto(int idUsuario, String usuario,
                       int idTag, String tag, int idCaderno, String caderno, int idUsuarioFKCad,
                       int idDocumento, String titulo, String previa, Date dataUltimaEdicao, Date dataProximaRevisao, int nivelRevisao, Status statusRevisao, int idCadernoFKDoc,
                       int idCompartilhamento, Permissao permissao, int idUsuarioFKCom, int idDocumentoFKCom,
                       int idTagDoc, int idDocumentoFKTD, int idTagFKTD) {
        setIdUsuario(idUsuario);
        setUsuario(usuario);

        setIdTag(idTag);
        setTag(tag);

        setIdCaderno(idCaderno);
        setCaderno(caderno);
        setIdUsuarioFKCad(idUsuarioFKCad);

        setIdDocumento(idDocumento);
        setTitulo(titulo);
        setPrevia(previa);
        setDataUltimaEdicao(dataUltimaEdicao);
        setDataProximaRevisao(dataProximaRevisao);
        setNivelRevisao(nivelRevisao);
        setStatusRevisao(statusRevisao);
        setIdCadernoFKDoc(idCadernoFKDoc);

        setIdCompartilhamento(idCompartilhamento);
        setPermissao(permissao);
        setIdUsuarioFKCom(idUsuarioFKCom);
        setIdDocumentoFKCom(idDocumentoFKCom);

        setIdTagDoc(idTagDoc);
        setIdDocumentoFKTD(idDocumentoFKTD);
        setIdTagFKTD(idTagFKTD);
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

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIdCaderno() {
        return idCaderno;
    }

    public void setIdCaderno(int idCaderno) {
        this.idCaderno = idCaderno;
    }

    public String getCaderno() {
        return caderno;
    }

    public void setCaderno(String caderno) {
        this.caderno = caderno;
    }

    public int getIdUsuarioFKCad() {
        return idUsuarioFKCad;
    }

    public void setIdUsuarioFKCad(int idUsuarioFKCad) {
        this.idUsuarioFKCad = idUsuarioFKCad;
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

    public Status getStatusRevisao() {
        return statusRevisao;
    }

    public void setStatusRevisao(Status statusRevisao) {
        this.statusRevisao = statusRevisao;
    }

    public int getIdCadernoFKDoc() {
        return idCadernoFKDoc;
    }

    public void setIdCadernoFKDoc(int idCadernoFKDoc) {
        this.idCadernoFKDoc = idCadernoFKDoc;
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

    public int getIdUsuarioFKCom() {
        return idUsuarioFKCom;
    }

    public void setIdUsuarioFKCom(int idUsuarioFKCom) {
        this.idUsuarioFKCom = idUsuarioFKCom;
    }

    public int getIdDocumentoFKCom() {
        return idDocumentoFKCom;
    }

    public void setIdDocumentoFKCom(int idDocumentoFKCom) {
        this.idDocumentoFKCom = idDocumentoFKCom;
    }

    public int getIdTagDoc() {
        return idTagDoc;
    }

    public void setIdTagDoc(int idTagDoc) {
        this.idTagDoc = idTagDoc;
    }

    public int getIdDocumentoFKTD() {
        return idDocumentoFKTD;
    }

    public void setIdDocumentoFKTD(int idDocumentoFKTD) {
        this.idDocumentoFKTD = idDocumentoFKTD;
    }

    public int getIdTagFKTD() {
        return idTagFKTD;
    }

    public void setIdTagFKTD(int idTagFKTD) {
        this.idTagFKTD = idTagFKTD;
    }

    @Override
    public String toString() {
        return "MenuDataDto: id do usuário:" +getIdUsuario();
    }
}
