package com.doydoit.springmvcjpa.web.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String memberName;

    @NotEmpty
    private String password;

    /*Address*/
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotNull
    private Integer zipcode;
}
