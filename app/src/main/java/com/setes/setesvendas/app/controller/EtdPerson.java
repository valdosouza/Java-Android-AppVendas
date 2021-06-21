package com.setes.setesvendas.app.controller;


public class EtdPerson
{

    String birthday;
    String cpf;
    String created_at;
    int id;
    String rg;
    int tb_profession_id;
    String updated_at;

    public EtdPerson(){

    }
    public EtdPerson(String birthday, String cpf, String created_at, int id, String rg, int tb_profession_id, String updated_at) {
        this.birthday = birthday;
        this.cpf = cpf;
        this.created_at = created_at;
        this.id = id;
        this.rg = rg;
        this.tb_profession_id = tb_profession_id;
        this.updated_at = updated_at;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getTb_profession_id() {
        return tb_profession_id;
    }

    public void setTb_profession_id(int tb_profession_id) {
        this.tb_profession_id = tb_profession_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
