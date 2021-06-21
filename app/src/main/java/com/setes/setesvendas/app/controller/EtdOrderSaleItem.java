package com.setes.setesvendas.app.controller;


public class EtdOrderSaleItem
{

    String created_at;
    double discount_aliquot;
    double discount_value;
    int id;
    int item;
    double qtty;
    int tb_institution_id;
    int tb_order_sale_id;
    int tb_product_id;
    int tb_salesman_id;
    double unit_value;
    String updated_at;

    public EtdOrderSaleItem(){

    }
    public EtdOrderSaleItem(String created_at, double discount_aliquot, double discount_value, int id, int item, double qtty, int tb_institution_id, int tb_order_sale_id, int tb_product_id, int tb_salesman_id, double unit_value, String updated_at) {
        this.created_at = created_at;
        this.discount_aliquot = discount_aliquot;
        this.discount_value = discount_value;
        this.id = id;
        this.item = item;
        this.qtty = qtty;
        this.tb_institution_id = tb_institution_id;
        this.tb_order_sale_id = tb_order_sale_id;
        this.tb_product_id = tb_product_id;
        this.tb_salesman_id = tb_salesman_id;
        this.unit_value = unit_value;
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getDiscount_aliquot() {
        return discount_aliquot;
    }

    public void setDiscount_aliquot(double discount_aliquot) {
        this.discount_aliquot = discount_aliquot;
    }

    public double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(double discount_value) {
        this.discount_value = discount_value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public double getQtty() {
        return qtty;
    }

    public void setQtty(double qtty) {
        this.qtty = qtty;
    }

    public int getTb_institution_id() {
        return tb_institution_id;
    }

    public void setTb_institution_id(int tb_institution_id) {
        this.tb_institution_id = tb_institution_id;
    }

    public int getTb_order_sale_id() {
        return tb_order_sale_id;
    }

    public void setTb_order_sale_id(int tb_order_sale_id) {
        this.tb_order_sale_id = tb_order_sale_id;
    }

    public int getTb_product_id() {
        return tb_product_id;
    }

    public void setTb_product_id(int tb_product_id) {
        this.tb_product_id = tb_product_id;
    }

    public int getTb_salesman_id() {
        return tb_salesman_id;
    }

    public void setTb_salesman_id(int tb_salesman_id) {
        this.tb_salesman_id = tb_salesman_id;
    }

    public double getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(double unit_value) {
        this.unit_value = unit_value;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
