package com.bcu.ccshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Goods extends BaseObservable {

    /**
     * 商品编号
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片地址
     */
    private String goodsImg;

    /**
     * 商品溯源
     */
    private String goodsOrigin;

    /**
     * 商品价格 单位是 分 ！
     */
    private String goodsPrice;

    /**
     * 商品是否促销
     */
    private Byte goodsIsPromote;

    /**
     * 商品促销价
     */
    private Float goodsPromotePrice;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 商品 是否可用积分
     */
    private Byte goodsIsUseScore;

    /**
     * 商品可用积分最大数量
     */
    private Integer goodsUseScoreMax;

    /**
     * 商品具体描述（长文本）
     */
    private String goodsDescription;

    /**
     * 商品已成团数量
     */
    private String goodsSoldAmount;


    @Bindable
    public String getGoodsSoldAmount() {
        return goodsSoldAmount;
    }

    public void setGoodsSoldAmount(String goodsSoldAmount) {
        this.goodsSoldAmount = goodsSoldAmount;
    }

    @Bindable
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    @Bindable
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    @Bindable
    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
    @Bindable
    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }
    @Bindable
    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    @Bindable
    public Byte getGoodsIsPromote() {
        return goodsIsPromote;
    }

    public void setGoodsIsPromote(Byte goodsIsPromote) {
        this.goodsIsPromote = goodsIsPromote;
    }
    @Bindable
    public Float getGoodsPromotePrice() {
        return goodsPromotePrice;
    }

    public void setGoodsPromotePrice(Float goodsPromotePrice) {
        this.goodsPromotePrice = goodsPromotePrice;
    }
    @Bindable
    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
    @Bindable
    public Byte getGoodsIsUseScore() {
        return goodsIsUseScore;
    }

    public void setGoodsIsUseScore(Byte goodsIsUseScore) {
        this.goodsIsUseScore = goodsIsUseScore;
    }
    @Bindable
    public Integer getGoodsUseScoreMax() {
        return goodsUseScoreMax;
    }

    public void setGoodsUseScoreMax(Integer goodsUseScoreMax) {
        this.goodsUseScoreMax = goodsUseScoreMax;
    }
    @Bindable
    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }




}