package com.bcu.ccshop.entity;

public class goodsInco {
    private String iId;
    private String name;
    private float price;

    public goodsInco(){}

    public goodsInco(String iId, String name, float price){
        this.iId=iId;
        this.name=name;
        this.price=price;
    }
    public String getiId() {
        return iId;
    }
    public void setiId(String iId) {
        this.iId = iId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
