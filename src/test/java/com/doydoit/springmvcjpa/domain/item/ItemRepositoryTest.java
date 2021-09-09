package com.doydoit.springmvcjpa.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        //then
        Assertions.assertThat(name).isEqualTo(findItem.getName());

        //미리 넣어둔 값에 영향을 받지 않을 테스트를 작성하도록 합니다.
        int index = list.size()-1;
        Assertions.assertThat(findItem).isEqualTo(list.get(index));
        
    }


}