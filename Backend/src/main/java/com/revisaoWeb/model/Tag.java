package com.revisaoWeb.model;

public class Tag {

    private int id;
    private String tag;

    public Tag() {}

    public Tag(int id, String tag) {
        setId(id);
        setTag(tag);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getTag() {
        return tag;
    }

    private void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag: " +getId() +"\n" +getTag();
    }

}
