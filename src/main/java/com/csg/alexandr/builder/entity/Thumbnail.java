package com.csg.alexandr.builder.entity;

public class Thumbnail {
    private String alt;
    private String imgSrc;
    private String title;
    private String comment;
    private String link;

    public Thumbnail() {
    }

    public Thumbnail(String title, String comment, String imgSrc, String alt, String link) {
        this.alt = alt;
        this.imgSrc = imgSrc;
        this.title = title;
        this.comment = comment;
        this.link = link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
