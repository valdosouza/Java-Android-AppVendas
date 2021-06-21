package com.setes.setesvendas.app.controller;


public class EtdMailing {

    String created_at;
    String email;
    int id;
    String kind;
    String news;
    String updated_at;

    public EtdMailing(){

    }

    public EtdMailing(String created_at, String email, int id, String kind, String news, String updated_at) {
        this.created_at = created_at;
        this.email = email;
        this.id = id;
        this.kind = kind;
        this.news = news;
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
