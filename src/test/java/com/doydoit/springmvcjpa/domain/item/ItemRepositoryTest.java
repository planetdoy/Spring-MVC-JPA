package com.doydoit.springmvcjpa.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save() {}

    @Test
    public void findById() {}

    @Test
    public void findAll() {
        //given
        String name = "aaaa";
        int price = 10000;
        int stockQuantity = 100;
        Item item = new Item(name, price, stockQuantity);

        itemRepository.save(item);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        Assertions.assertThat(name).isEqualTo(items.get(0).getName());
    }
}