package com.mongo.manager.homework;


public class OrdOrderDto {

    private String day;
    private String orderId;
    private String orderAmount;

    public String getDay() {
        return day;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
