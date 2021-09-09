package com.doydoit.springmvcjpa.domain.delivery;

import com.doydoit.springmvcjpa.domain.Address;
import com.doydoit.springmvcjpa.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Long id;

    @Enumerated
    private DeliveryStatus deliveryStatus;

    @OneToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    private Address address;

    public Delivery(DeliveryStatus deliveryStatus, Order order, Address address) {
        this.deliveryStatus = deliveryStatus;
        this.order = order;
        this.address = address;
    }
}