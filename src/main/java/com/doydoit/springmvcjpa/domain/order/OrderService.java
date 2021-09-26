package com.doydoit.springmvcjpa.domain.order;

import com.doydoit.springmvcjpa.domain.delivery.Delivery;
import com.doydoit.springmvcjpa.domain.delivery.DeliveryStatus;
import com.doydoit.springmvcjpa.domain.item.Item;
import com.doydoit.springmvcjpa.domain.item.ItemRepository;
import com.doydoit.springmvcjpa.domain.member.Member;
import com.doydoit.springmvcjpa.domain.member.MemberRepository;
import com.doydoit.springmvcjpa.domain.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void createOrder(Long itemId, Long memberId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery(DeliveryStatus.READY, member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, count, item.getPrice());

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
    }
}
