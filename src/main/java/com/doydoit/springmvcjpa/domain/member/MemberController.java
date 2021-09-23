package com.doydoit.springmvcjpa.domain.member;

import com.doydoit.springmvcjpa.domain.Address;
import com.doydoit.springmvcjpa.web.member.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new MemberSaveForm());
        return "member/addForm";
    }

    /**
     * 회원가입
     */
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("member") MemberSaveForm form, BindingResult bindingResult) {

        // TODO 리팩토링
        List<Member> sameIdMember = memberService.findByLoginId(form.getLoginId());
        if (sameIdMember.size() != 0) {
            bindingResult.rejectValue("loginId","error.already","이미 존재하는 아이디 입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }


        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getLoginId(), form.getMemberName(), form.getPassword(), address);
        memberService.save(member);

        return "redirect:/";
    }

}
