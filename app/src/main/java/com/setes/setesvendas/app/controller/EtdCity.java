package com.setes.setesvendas.app.controller;


public class EtdCity
{

    String created_at;
    String ibge;
    int id;
    String name;
    int tb_state_id;
    String updated_at;
    String zip_code;

    public EtdCity(){

    }
    public EtdCity(String created_at, String ibge, int id, String name, int tb_state_id, String updated_at, String zip_code) {
        this.created_at = created_at;
        this.ibge = ibge;
        this.id = id;
        this.name = name;
        this.tb_state_id = tb_state_id;
        this.updated_at = updated_at;
        this.zip_code = zip_code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
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

    public int getTb_state_id() {
        return tb_state_id;
    }

    public void setTb_state_id(int tb_state_id) {
        this.tb_state_id = tb_state_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
