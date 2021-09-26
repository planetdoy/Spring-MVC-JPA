package com.doydoit.springmvcjpa.domain.order;

import com.doydoit.springmvcjpa.domain.delivery.Delivery;
import com.doydoit.springmvcjpa.domain.orderItem.OrderItem;
import com.doydoit.springmvcjpa.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected void setMember(Member member) {
        this.member = member;
    }

    protected void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    protected void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem orderItem) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.addOrderItem(orderItem);
        order.setOrderDate(LocalDateTime.now());
        order.orderStatus = OrderStatus.ORDER;

        return order;
    }
}