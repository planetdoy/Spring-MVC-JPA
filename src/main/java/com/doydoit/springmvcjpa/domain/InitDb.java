package com.doydoit.springmvcjpa.domain;

import com.doydoit.springmvcjpa.domain.item.Item;
import com.doydoit.springmvcjpa.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * userA
 *  JPA1 BOOK
 *  JPA2 BOOK
 * userB
 *  SPRING1 BOOK
 *  SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
//        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

            String loginId = "admin";
            String memberName = "관리자";
            String password = "admin!";
            AccessRole role = AccessRole.ADMIN;
            Address address = new Address("city", "street", 1111);

            Member member = new Member(loginId, password, memberName,  role, address);
            em.persist(member);

            String loginId2 = "user";
            String memberName2 = "유저";
            String password2 = "1234";
            AccessRole role2 = AccessRole.USER;
            Address address2 = new Address("city2", "street2", 2222);

            Member member2 = new Member(loginId2, password2, memberName2,  role2, address2);
            em.persist(member2);

            String name = "test_init_item_1";
            int price = 1000000;
            int stockQuantity = 100;

            Item item = new Item(name, price, stockQuantity);

            em.persist(item);
        }
        public void dbInit2() {

            String name = "test_init_item_2";
            int price = 20000;
            int stockQuantity = 10;

            Item item = new Item(name, price, stockQuantity);

            em.persist(item);
        }
    }
}