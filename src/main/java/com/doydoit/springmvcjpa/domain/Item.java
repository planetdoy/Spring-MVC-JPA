package com.doydoit.springmvcjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;

    @NotEmpty(message = "상품 명을 입력해주세요.")
    private String name;

    @NotEmpty(message = "가격은 1000원 이상 입니다.")
    private int price;

    @NotEmpty(message = "수량은 0 이상 입니다.")
    private int stockQuantity;
}
