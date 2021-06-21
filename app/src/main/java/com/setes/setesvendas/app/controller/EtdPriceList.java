package com.setes.setesvendas.app.controller;


public class EtdPriceList
{

    Double aliq_profit;
    String created_at;
    String description;
    int id;
    String modality;
    int tb_institution_id;
    String updated_at;
    String validity;

    public EtdPriceList(){

    }
    public EtdPriceList(Double aliq_profit, String created_at, String description, int id, String modality, int tb_institution_id, String updated_at, String validity) {
        this.aliq_profit = aliq_profit;
        this.created_at = created_at;
        this.description = description;
        this.id = id;
        this.modality = modality;
        this.tb_institution_id = tb_institution_id;
        this.updated_at = updated_at;
        this.validity = validity;
    }

    public Double getAliq_profit() {
        return aliq_profit;
    }

    public void setAliq_profit(Double aliq_profit) {
        this.aliq_profit = aliq_profit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public int getTb_institution_id() {
        return tb_institution_id;
    }

    public void setTb_institution_id(int tb_institution_id) {
        this.tb_institution_id = tb_institution_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
