package com.doydoit.springmvcjpa.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll() {
        List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
        return orders;
    }

    public List<Order> findOrderByMemberId(Long memberId) {

        return em.createQuery("select o from Order o join o.member m where m.id=:memberId",Order.class)
                .setParameter("memberId", memberId).getResultList();
    }
}
