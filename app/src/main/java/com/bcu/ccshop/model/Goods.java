package com.bcu.ccshop.model;

import java.util.Date;

public class Goods {
    /**
     * 商品编号
     */
    private String goodsId;

    /**
     * 商品所属类别编号
     */
    private String goodsCategoryId;

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
    private Float goodsPrice;

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
     * 商品最低成团购买数量
     */
    private Float goodsLowerLimit;

    /**
     * 商品总数
     */
    private Float goodsTotalAmount;

    /**
     * 商品已成团数量
     */
    private Float goodsSoldAmount;

    /**
     * 是否是扶贫商品
     */
    private Byte goodsIsHelper;

    /**
     * 商品供应范围
     */
    private String goodsSupplyRange;

    /**
     * 商品(团) 起始售卖时间
     */
    private Date goodsStartTime;

    /**
     * 商品(团) 截止售卖时间
     */
    private Date goodsEndTime;

    /**
     * 商品 是否可用积分
     */
    private Byte goodsIsUseScore;

    /**
     * 商品可用积分最大数量
     */
    private Integer goodsUseScoreMax;

    /**
     * 商品是否返积分
     */
    private Byte goodsIsBackScore;

    /**
     * 商品最大反积分数量
     */
    private Integer goodsBackScoreAccount;

    /**
     * 商品状态
     */
    private Integer goodsStatus;

    /**
     * 商品具体描述（长文本）
     */
    private String goodsDescription;


    /**
     * 商品类别
     * （1：扶贫商品 2：日用品）
     */
    private String goodsType;



    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(String goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Byte getGoodsIsPromote() {
        return goodsIsPromote;
    }

    public void setGoodsIsPromote(Byte goodsIsPromote) {
        this.goodsIsPromote = goodsIsPromote;
    }

    public Float getGoodsPromotePrice() {
        return goodsPromotePrice;
    }

    public void setGoodsPromotePrice(Float goodsPromotePrice) {
        this.goodsPromotePrice = goodsPromotePrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Float getGoodsLowerLimit() {
        return goodsLowerLimit;
    }

    public void setGoodsLowerLimit(Float goodsLowerLimit) {
        this.goodsLowerLimit = goodsLowerLimit;
    }

    public Float getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Float goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public Float getGoodsSoldAmount() {
        return goodsSoldAmount;
    }

    public void setGoodsSoldAmount(Float goodsSoldAmount) {
        this.goodsSoldAmount = goodsSoldAmount;
    }

    public Byte getGoodsIsHelper() {
        return goodsIsHelper;
    }

    public void setGoodsIsHelper(Byte goodsIsHelper) {
        this.goodsIsHelper = goodsIsHelper;
    }

    public String getGoodsSupplyRange() {
        return goodsSupplyRange;
    }

    public void setGoodsSupplyRange(String goodsSupplyRange) {
        this.goodsSupplyRange = goodsSupplyRange;
    }

    public Date getGoodsStartTime() {
        return goodsStartTime;
    }

    public void setGoodsStartTime(Date goodsStartTime) {
        this.goodsStartTime = goodsStartTime;
    }

    public Date getGoodsEndTime() {
        return goodsEndTime;
    }

    public void setGoodsEndTime(Date goodsEndTime) {
        this.goodsEndTime = goodsEndTime;
    }

    public Byte getGoodsIsUseScore() {
        return goodsIsUseScore;
    }

    public void setGoodsIsUseScore(Byte goodsIsUseScore) {
        this.goodsIsUseScore = goodsIsUseScore;
    }

    public Integer getGoodsUseScoreMax() {
        return goodsUseScoreMax;
    }

    public void setGoodsUseScoreMax(Integer goodsUseScoreMax) {
        this.goodsUseScoreMax = goodsUseScoreMax;
    }

    public Byte getGoodsIsBackScore() {
        return goodsIsBackScore;
    }

    public void setGoodsIsBackScore(Byte goodsIsBackScore) {
        this.goodsIsBackScore = goodsIsBackScore;
    }

    public Integer getGoodsBackScoreAccount() {
        return goodsBackScoreAccount;
    }

    public void setGoodsBackScoreAccount(Integer goodsBackScoreAccount) {
        this.goodsBackScoreAccount = goodsBackScoreAccount;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCategoryId=").append(goodsCategoryId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsImg=").append(goodsImg);
        sb.append(", goodsOrigin=").append(goodsOrigin);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsIsPromote=").append(goodsIsPromote);
        sb.append(", goodsPromotePrice=").append(goodsPromotePrice);
        sb.append(", goodsUnit=").append(goodsUnit);
        sb.append(", goodsLowerLimit=").append(goodsLowerLimit);
        sb.append(", goodsTotalAmount=").append(goodsTotalAmount);
        sb.append(", goodsSoldAmount=").append(goodsSoldAmount);
        sb.append(", goodsIsHelper=").append(goodsIsHelper);
        sb.append(", goodsSupplyRange=").append(goodsSupplyRange);
        sb.append(", goodsStartTime=").append(goodsStartTime);
        sb.append(", goodsEndTime=").append(goodsEndTime);
        sb.append(", goodsIsUseScore=").append(goodsIsUseScore);
        sb.append(", goodsUseScoreMax=").append(goodsUseScoreMax);
        sb.append(", goodsIsBackScore=").append(goodsIsBackScore);
        sb.append(", goodsBackScoreAccount=").append(goodsBackScoreAccount);
        sb.append(", goodsStatus=").append(goodsStatus);
        sb.append(", goodsDescription=").append(goodsDescription);
        sb.append("]");
        return sb.toString();
    }

}