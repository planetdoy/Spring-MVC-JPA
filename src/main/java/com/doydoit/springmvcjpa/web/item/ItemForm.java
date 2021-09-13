package com.doydoit.springmvcjpa.web.item;

import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer stockQuantity;
}
