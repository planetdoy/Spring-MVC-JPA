package com.doydoit.springmvcjpa.web.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberSaveForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String memberName;

    @NotEmpty
    private String password;
}
