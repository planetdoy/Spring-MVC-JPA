package com.doydoit.springmvcjpa.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Transactional
    @Test
    void save_findById_findAll() {
        //given
        String name = "item1";
        int price = 10000;
        int stockQuantity = 100;

        Item item = new Item(name, price, stockQuantity);

        //when
        itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());
        List<Item> list = itemRepository.findAll();
        System.out.println("list.get(0).getName() = " + list.get(0).getName());

        //then
        assertThat(name).isEqualTo(findItem.getName());
        assertThat(findItem).isEqualTo(list.get(0));
    }


}