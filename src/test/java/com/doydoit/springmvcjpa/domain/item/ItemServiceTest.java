package com.doydoit.springmvcjpa.domain.item;

import com.doydoit.springmvcjpa.web.item.ItemForm;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void findAll() {
        //given


        //when


        //then
    }

    @Test
    public void findById() {
        //given


        //when


        //then
    }

    @Test
    public void update() {
        //given
        String name = "test_test";
        int price = 999;
        int stockQuantity = 999;

        Long originId = itemService.findAll().get(0).getId();
        ItemForm form = new ItemForm(name, price, stockQuantity);
        //when
        Item updateItem = itemService.update(originId, form);

        //then
        Assertions.assertThat(updateItem.getName()).isEqualTo(name);
    }
}