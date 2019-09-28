package com.bcu.ccshop.dataTranformer;

import java.util.Date;

public class Order {
    /**
     * 订单编号
     */
    private String orderId;


    /**
     * 订单图片
     */
    private String orderImage;

    /**
     * 订单商品编号
     */
    private String orderGoodsId;

    /**
     * 订单用户编号
     */
    private String orderUserId;

    /**
     * 订单用户OpenId
     */
    private String orderOpenId;

    public String getOrderOpenId() {
        return orderOpenId;
    }

    public void setOrderOpenId(String orderOpenId) {
        this.orderOpenId = orderOpenId;
    }

    /**
     * 订单批次编号
     */
    private String orderBatchId;

    /**
     * 订单团编号
     */
    private String orderGroupId;

    /**
     * 订单配送网点编号
     */
    private String orderOutletsId;

    /**
     * 订单支付编号
     */
    private String orderDeliveryId;

    /**
     * 订单快递号
     */
    private String orderShippingId;

    /**
     * 订单快递公司名
     */
    private String orderShippingName;

    /**
     * 收货人
     */
    private String orderConsignee;

    /**
     * 收货人联系方式
     */
    private String orderTel;

    /**
     * 商品购买数量
     */
    private String orderBuyCount;

    /**
     * 订单总费用
     */
    private Double orderTotalFee;

    /**
     * 商品费用
     */
    private Double orderGoodsFee;

    /**
     * 快递费用
     */
    private Double orderShippingFee;

    /**
     * 积分变动数量
     * 获得积分为正数，使用积分为负数
     */
    private String orderScore;

    public String getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(String orderScore) {
        this.orderScore = orderScore;
    }

    /**
     * 订单备注
     */
    private String orderPostscript;

    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    /**
     * 订单支付时间
     */
    private Date orderPayTime;

    /**
     * 订单状态
     * -3：关闭
     * -2：待退款
     * -1：已取消
     * 0：待支付
     * 1：已支付，等待拼团结束
     * 4：运输中
     * 6：待取货
     * 7：待评价
     * 8：已完成
     */
    private String orderStatus;

   private Date orderFinishTime;
   public String getOrderImage(){
       return orderImage;
   }
   public  void setOrderImage(){
       this.orderImage=orderImage;
   }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderBatchId() {
        return orderBatchId;
    }

    public void setOrderBatchId(String orderBatchId) {
        this.orderBatchId = orderBatchId;
    }

    public String getOrderGroupId() {
        return orderGroupId;
    }

    public void setOrderGroupId(String orderGroupId) {
        this.orderGroupId = orderGroupId;
    }

    public String getOrderOutletsId() {
        return orderOutletsId;
    }

    public void setOrderOutletsId(String orderOutletsId) {
        this.orderOutletsId = orderOutletsId;
    }

    public String getOrderDeliveryId() {
        return orderDeliveryId;
    }

    public void setOrderDeliveryId(String orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public String getOrderShippingId() {
        return orderShippingId;
    }

    public void setOrderShippingId(String orderShippingId) {
        this.orderShippingId = orderShippingId;
    }

    public String getOrderShippingName() {
        return orderShippingName;
    }

    public void setOrderShippingName(String orderShippingName) {
        this.orderShippingName = orderShippingName;
    }

    public String getOrderConsignee() {
        return orderConsignee;
    }

    public void setOrderConsignee(String orderConsignee) {
        this.orderConsignee = orderConsignee;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public String getOrderBuyCount() {
        return orderBuyCount;
    }

    public void setOrderBuyCount(String orderBuyCount) {
        this.orderBuyCount = orderBuyCount;
    }

    public Double getOrderTotalFee() {
        return orderTotalFee;
    }

    public void setOrderTotalFee(Double orderTotalFee) {
        this.orderTotalFee = orderTotalFee;
    }

    public Double getOrderGoodsFee() {
        return orderGoodsFee;
    }

    public void setOrderGoodsFee(Double orderGoodsFee) {
        this.orderGoodsFee = orderGoodsFee;
    }

    public Double getOrderShippingFee() {
        return orderShippingFee;
    }

    public void setOrderShippingFee(Double orderShippingFee) {
        this.orderShippingFee = orderShippingFee;
    }



    public String getOrderPostscript() {
        return orderPostscript;
    }

    public void setOrderPostscript(String orderPostscript) {
        this.orderPostscript = orderPostscript;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderImage=").append(orderImage);
        sb.append(", orderGoodsId=").append(orderGoodsId);
        sb.append(", orderUserId=").append(orderUserId);
        sb.append(", orderBatchId=").append(orderBatchId);
        sb.append(", orderGroupId=").append(orderGroupId);
        sb.append(", orderOutletsId=").append(orderOutletsId);
        sb.append(", orderDeliveryId=").append(orderDeliveryId);
        sb.append(", orderShippingId=").append(orderShippingId);
        sb.append(", orderShippingName=").append(orderShippingName);
        sb.append(", orderConsignee=").append(orderConsignee);
        sb.append(", orderTel=").append(orderTel);
        sb.append(", orderBuyCount=").append(orderBuyCount);
        sb.append(", orderTotalFee=").append(orderTotalFee);
        sb.append(", orderGoodsFee=").append(orderGoodsFee);
        sb.append(", orderShippingFee=").append(orderShippingFee);
        sb.append(", orderUseScoreCount=").append(orderScore);
        sb.append(", orderPostscript=").append(orderPostscript);
        sb.append(", orderCreateTime=").append(orderCreateTime);
        sb.append(", orderPayTime=").append(orderPayTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append("]");
        return sb.toString();
    }
}