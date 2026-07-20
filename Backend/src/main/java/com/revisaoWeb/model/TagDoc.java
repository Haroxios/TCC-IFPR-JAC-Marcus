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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "TagDoc: " +getId() +"\n" +getDocumento() +"\n" +getTag();
    }
}
