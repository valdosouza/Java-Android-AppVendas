package com.setes.setesvendas.app.controller;

public class EtdEntity
{

    String created_at;
    int id;
    String name_company;
    String nick_trade;
    String updated_at;

    public EtdEntity(){

    }
    public EtdEntity(String created_at, int id, String name_company, String nick_trade, String updated_at) {
        this.created_at = created_at;
        this.id = id;
        this.name_company = name_company;
        this.nick_trade = nick_trade;
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

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getNick_trade() {
        return nick_trade;
    }

    public void setNick_trade(String nick_trade) {
        this.nick_trade = nick_trade;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
