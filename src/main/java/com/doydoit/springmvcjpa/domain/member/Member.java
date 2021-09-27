package com.doydoit.springmvcjpa.domain.member;

import com.doydoit.springmvcjpa.domain.AccessRole;
import com.doydoit.springmvcjpa.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String memberName;

    @Enumerated(EnumType.STRING)
    private AccessRole role;

    @Embedded
    private Address address;

    public Member(String loginId, String password, String memberName, AccessRole role, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.memberName = memberName;
        this.role = role;
        this.address = address;
    }
}
