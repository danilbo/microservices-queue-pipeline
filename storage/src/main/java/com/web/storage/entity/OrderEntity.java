package com.web.storage.entity;

import com.web.storage.dto.OrderStage;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    protected OrderEntity() {
    }

    public OrderEntity(long orderID, OrderStage stage) {
        this.orderID = orderID;
        this.stage = stage;
    }

    @Id
    @Column(name = "id")
    private long orderID;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage")
    OrderStage stage;


    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public OrderStage getStage() {
        return stage;
    }

    public void setStage(OrderStage stage) {
        this.stage = stage;
    }
}
