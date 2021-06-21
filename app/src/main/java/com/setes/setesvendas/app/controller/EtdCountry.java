package com.setes.setesvendas.app.controller;


public class EtdCountry
{

    String created_at;
    int id;
    String name;
    String updated_at;

    public EtdCountry(){

    }
    public EtdCountry(String created_at, int id, String name, String updated_at) {
        this.created_at = created_at;
        this.id = id;
        this.name = name;
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
