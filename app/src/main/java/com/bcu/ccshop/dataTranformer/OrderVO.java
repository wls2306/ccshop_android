package com.bcu.ccshop.dataTranformer;

public class OrderVO {

    /**
     * orderImage : images/goods/FA0257/FA0257-0.jpg
     * orderSinglePrice : 18.8
     * orderAddress : 北京城市学院
     * orderId : G20190612142641542
     * orderTotalFee : 4.99
     * createTime : 2019-06-12 14-26-41
     * orderFinishTime : 2019-06-12 14-30-00
     * orderBuyCount : 1
     * orderName : 阜平花山香菇干200g 珍珠菇 蘑菇 菌菇 无根 南北山珍干货 煲汤红烧蒸蛋
     */

    private String orderImage;
    private float orderSinglePrice;
    private String orderAddress;
    private String orderId;
    private float orderTotalFee;
    private String createTime;
    private String orderFinishTime;
    private String orderBuyCount;
    private String orderName;

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public float getOrderSinglePrice() {
        return orderSinglePrice;
    }

    public void setOrderSinglePrice(float orderSinglePrice) {
        this.orderSinglePrice = orderSinglePrice;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getOrderTotalFee() {
        return orderTotalFee;
    }

    public void setOrderTotalFee(float orderTotalFee) {
        this.orderTotalFee = orderTotalFee;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getOrderBuyCount() {
        return orderBuyCount;
    }

    public void setOrderBuyCount(String orderBuyCount) {
        this.orderBuyCount = orderBuyCount;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
