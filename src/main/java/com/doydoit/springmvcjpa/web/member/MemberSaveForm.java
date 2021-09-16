package com.doydoit.springmvcjpa.web.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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
}
