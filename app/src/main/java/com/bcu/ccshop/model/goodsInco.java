package com.bcu.ccshop.model;

import android.graphics.Bitmap;

public class goodsInco {
    private String iId;
    private String name;
    private float price;
    private Bitmap item_img;

    public goodsInco(){}

    public goodsInco(String iId, String name, float price,Bitmap item_img){
        this.iId=iId;
        this.name=name;
        this.price=price;
        this.item_img=item_img;
    }
    public Bitmap getItem_img(){return item_img;}
    public void setItem_img(Bitmap item_img){this.item_img=item_img;}
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
