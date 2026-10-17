package com.revisaoWeb.model;

public class TagDoc {

    private int idTagDoc;
    private Documento documento;
    private Tag tag;

    public TagDoc() {}

    public TagDoc(int idTagDoc, Documento documento, Tag tag) {
        setIdTagDoc(idTagDoc);
        setDocumento(documento);
        setTag(tag);
    }

    public int getIdTagDoc() {
        return idTagDoc;
    }

    public void setIdTagDoc(int idTagDoc) {
        this.idTagDoc = idTagDoc;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TagDoc: " +getIdTagDoc() +"\n" +getDocumento() +"\n" +getTag();
    }
}
