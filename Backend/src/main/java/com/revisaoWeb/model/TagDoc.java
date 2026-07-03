package com.revisaoWeb.model;

public class TagDoc {

    private int id;
    private Documento documento;
    private Tag tag;

    public TagDoc() {}

    public TagDoc(int id, Documento documento, Tag tag) {
        setId(id);
        setDocumento(documento);
        setTag(tag);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Documento getDocumento() {
        return documento;
    }

    private void setDocumento(Documento documento) {
        this.documento = documento;
    }

    private Tag getTag() {
        return tag;
    }

    private void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TagDoc: " +getId() +"\n" +getDocumento() +"\n" +getTag();
    }
}
