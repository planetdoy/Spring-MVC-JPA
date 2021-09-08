package com.doydoit.springmvcjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {

    private Long id;
    private Order order;
    private Item item;
    private int count;
    private int orderPrice;
}
