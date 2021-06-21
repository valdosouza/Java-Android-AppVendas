package com.setes.setesvendas.app.controller;


public class EtdState
{

    String created_at;
    int id;
    String name;
    String sigla;
    String updated_at;

    public EtdState(){

    }
    public EtdState(String created_at, int id, String name, String sigla, String updated_at) {
        this.created_at = created_at;
        this.id = id;
        this.name = name;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
