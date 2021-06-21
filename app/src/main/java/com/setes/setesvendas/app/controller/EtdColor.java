package com.setes.setesvendas.app.controller;


public class EtdColor
{

    String ItDescription;
    String created_at;
    int itId;
    String updated_at;

    public EtdColor(String itDescription, String created_at, int itId, String updated_at) {
        ItDescription = itDescription;
        this.created_at = created_at;
        this.itId = itId;
        this.updated_at = updated_at;
    }

    public String getItDescription() {
        return ItDescription;
    }

    public void setItDescription(String itDescription) {
        ItDescription = itDescription;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getItId() {
        return itId;
    }

    public void setItId(int itId) {
        this.itId = itId;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
