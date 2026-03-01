package com.web.storage.dto;


import java.util.List;

public class OrderRequest {


private long orderID;

private OrderStage stage;

private long userID;

private List<Long> itemIDs;

private String address;

private String comment;

    public OrderRequest() {
        // обязательно для Jackson
    }
    public OrderRequest(long orderID, OrderStage stage) {
        this.orderID = orderID;
        this.stage = stage;
    }

    public OrderStage getStage() {
        return stage;
    }

    public void setStage(OrderStage stage) {
        this.stage = stage;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public List<Long> getItemIDs() {
        return itemIDs;
    }

    public void setItemIDs(List<Long> itemIDs) {
        this.itemIDs = itemIDs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderID=" + orderID +
                ", stage=" + stage +
                ", userID=" + userID +
                ", itemIDs=" + itemIDs +
                ", address='" + address + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}