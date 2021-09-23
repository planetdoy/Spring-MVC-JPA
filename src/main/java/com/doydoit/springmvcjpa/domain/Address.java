package com.doydoit.springmvcjpa.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String street;
    private Integer zipcode;
}
