package com.revisaoWeb.model;

public class Tag {

    private int id;
    private String tag;

    public Tag() {}

    public Tag(int id, String tag) {
        setId(id);
        setTag(tag);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag: " +getId() +"\n" +getTag();
    }

}
