package com.doydoit.springmvcjpa.domain.orderItem;

import com.doydoit.springmvcjpa.domain.item.Item;
import com.doydoit.springmvcjpa.domain.order.Order;
import lombok.*;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
    private int orderPrice;

    public OrderItem(Item item, int count, int orderPrice) {
        this.item = item;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public static OrderItem createOrderItem(Item item, int count, int orderPrice) {

        OrderItem orderItem = new OrderItem(item, count, orderPrice);
        item.removeStock(count);
        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
