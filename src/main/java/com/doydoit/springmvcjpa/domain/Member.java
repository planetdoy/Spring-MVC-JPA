package com.doydoit.springmvcjpa.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String loginPw;

    @NotNull
    private String name;

    @Enumerated
    private AccessRole role;

    @Embedded
    private Address address;
}
