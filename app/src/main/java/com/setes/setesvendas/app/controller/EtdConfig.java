package com.setes.setesvendas.app.controller;


public class EtdConfig
{

    String content;
    String id;

    public EtdConfig(){

    }

    public EtdConfig(String content, String id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
