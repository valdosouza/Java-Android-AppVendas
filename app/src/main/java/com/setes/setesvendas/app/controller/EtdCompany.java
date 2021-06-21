package com.setes.setesvendas.app.controller;


public class EtdCompany
{

    String cnpj;
    String created_at;
    String dt_foundation;
    int id;
    String ie;
    String iest;
    String im;
    String updated_at;

    public EtdCompany(){

    }
    public EtdCompany(String cnpj, String created_at, String dt_foundation, int id, String ie, String iest, String im, String updated_at) {
        this.cnpj = cnpj;
        this.created_at = created_at;
        this.dt_foundation = dt_foundation;
        this.id = id;
        this.ie = ie;
        this.iest = iest;
        this.im = im;
        this.updated_at = updated_at;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDt_foundation() {
        return dt_foundation;
    }

    public void setDt_foundation(String dt_foundation) {
        this.dt_foundation = dt_foundation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIest() {
        return iest;
    }

    public void setIest(String iest) {
        this.iest = iest;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
