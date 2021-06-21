package com.setes.setesvendas.app.controller;


public class EtdPhone
{

    String address_kind;
    String contact;
    String created_at;
    int id;
    String kind;
    String number;
    String updated_at;

    public EtdPhone(){

    }
    public EtdPhone(String address_kind, String contact, String created_at, int id, String kind, String number, String updated_at) {
        this.address_kind = address_kind;
        this.contact = contact;
        this.created_at = created_at;
        this.id = id;
        this.kind = kind;
        this.number = number;
        this.updated_at = updated_at;
    }

    public String getAddress_kind() {
        return address_kind;
    }

    public void setAddress_kind(String address_kind) {
        this.address_kind = address_kind;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
