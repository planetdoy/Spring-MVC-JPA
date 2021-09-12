package com.doydoit.springmvcjpa.web.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ItemForm {

    private String itemName;

    private Integer price;

    private Integer stockQuantity;
}
