package com.doydoit.springmvcjpa.web.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemForm {

    private String name;

    private Integer price;

    private Integer stockQuantity;
}
