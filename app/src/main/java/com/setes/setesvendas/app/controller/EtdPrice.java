package com.setes.setesvendas.app.controller;


public class EtdPrice
{

    double aliq_kickback;
    double aliq_profit;
    String created_at;
    double price_tag;
    double quantity;
    int tb_institution_id;
    int tb_price_list_id;
    int tb_product_id;
    String updated_at;

    public EtdPrice(){

    }
    public EtdPrice(double aliq_kickback, double aliq_profit, String created_at, double price_tag, double quantity, int tb_institution_id, int tb_price_list_id, int tb_product_id, String updated_at) {
        this.aliq_kickback = aliq_kickback;
        this.aliq_profit = aliq_profit;
        this.created_at = created_at;
        this.price_tag = price_tag;
        this.quantity = quantity;
        this.tb_institution_id = tb_institution_id;
        this.tb_price_list_id = tb_price_list_id;
        this.tb_product_id = tb_product_id;
        this.updated_at = updated_at;
    }

    public double getAliq_kickback() {
        return aliq_kickback;
    }

    public void setAliq_kickback(double aliq_kickback) {
        this.aliq_kickback = aliq_kickback;
    }

    public double getAliq_profit() {
        return aliq_profit;
    }

    public void setAliq_profit(double aliq_profit) {
        this.aliq_profit = aliq_profit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getPrice_tag() {
        return price_tag;
    }

    public void setPrice_tag(double price_tag) {
        this.price_tag = price_tag;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getTb_institution_id() {
        return tb_institution_id;
    }

    public void setTb_institution_id(int tb_institution_id) {
        this.tb_institution_id = tb_institution_id;
    }

    public int getTb_price_list_id() {
        return tb_price_list_id;
    }

    public void setTb_price_list_id(int tb_price_list_id) {
        this.tb_price_list_id = tb_price_list_id;
    }

    public int getTb_product_id() {
        return tb_product_id;
    }

    public void setTb_product_id(int tb_product_id) {
        this.tb_product_id = tb_product_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
