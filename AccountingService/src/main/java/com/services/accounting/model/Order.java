package com.services.accounting.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "accounting", name = "order")
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public class Order implements Serializable {
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable=false)
    private Client client;

    public Order(Client client) {
        this.client = client;
        this.orderStatus = OrderStatus.COLLECTING;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
