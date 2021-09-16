package com.doydoit.springmvcjpa.domain.member;

import com.doydoit.springmvcjpa.domain.AccessRole;
import com.doydoit.springmvcjpa.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String memberName;

//    @Enumerated
//    private AccessRole role;
//
//    @Embedded
//    private Address address;

    public Member(String loginId, String memberName, String password) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
    }
}
