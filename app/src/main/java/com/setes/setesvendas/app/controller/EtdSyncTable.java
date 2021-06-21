package com.setes.setesvendas.app.controller;


public class EtdSyncTable
{

    String direction;
    String id;
    String updateAt;

    public EtdSyncTable(){

    }
    public EtdSyncTable(String direction, String id, String updateAt) {
        this.direction = direction;
        this.id = id;
        this.updateAt = updateAt;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
