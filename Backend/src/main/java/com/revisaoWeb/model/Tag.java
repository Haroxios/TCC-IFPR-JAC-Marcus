package com.revisaoWeb.model;

public class Tag {

    private int idTag;
    private String tag;

    public Tag() {}

    public Tag(int idTag, String tag) {
        setIdTag(idTag);
        setTag(tag);
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

    @Override
    public String toString() {
        return "Tag: " +getIdTag() +"\n" +getTag();
    }

}
