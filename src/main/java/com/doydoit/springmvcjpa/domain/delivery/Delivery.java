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
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Delivery(DeliveryStatus deliveryStatus, Address address) {
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}