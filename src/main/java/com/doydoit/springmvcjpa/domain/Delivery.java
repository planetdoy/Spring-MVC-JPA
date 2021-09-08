package com.doydoit.springmvcjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
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

}