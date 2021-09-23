package com.doydoit.springmvcjpa.web.item;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {

    private Long id;

    private String itemName;

    @NumberFormat(pattern = "###,###")
    private Integer price;

    @NumberFormat(pattern = "###,###")
    private Integer stockQuantity;
}
