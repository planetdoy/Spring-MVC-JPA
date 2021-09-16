package com.doydoit.springmvcjpa.domain.member;

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

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("member", new MemberSaveForm());
        return "member/addForm";
    }

    @PostMapping("/add")
    public String addForm(@Validated @ModelAttribute("member") MemberSaveForm form, BindingResult bindingResult) {

        // TODO 리팩토링
        List<Member> sameIdMember = memberService.findByLoginId(form.getLoginId());
        if (sameIdMember.size() != 0) {
            bindingResult.rejectValue("loginId","error.already","이미 존재하는 아이디 입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }

        Member member = new Member(form.getLoginId(), form.getMemberName(), form.getPassword());
        memberService.save(member);

        return "redirect:/";
    }
}
