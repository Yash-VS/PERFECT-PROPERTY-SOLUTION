package com.example.ppt_application.model;


public class TemplateModel {
    String name, id;
    Integer imageUrl;

    public TemplateModel(Integer iurl , String name, String id) {
        this.id = id;
        this.imageUrl = iurl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
}

