package com.doydoit.springmvcjpa.domain.member;

import com.doydoit.springmvcjpa.domain.AccessRole;
import com.doydoit.springmvcjpa.domain.Address;
import com.doydoit.springmvcjpa.domain.login.SessionConst;
import com.doydoit.springmvcjpa.web.member.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        List<Member> sameIdMember = memberService.findByLoginId(form.getLoginId());
        if (!sameIdMember.isEmpty()) {
            bindingResult.rejectValue("loginId", "error.already", "이미 존재하는 아이디 입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }


        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getLoginId(), form.getPassword(), form.getMemberName(), AccessRole.USER, address);
        memberService.save(member);

        return "redirect:/";
    }

    @GetMapping("/info")
    public String info(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.Login_Member);
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);

        return "member/info";
    }

    @PostMapping("/address/editForm")
    public String editForm(Address address, Model model) {
        model.addAttribute("address", address);
        return "member/infoEditForm";
    }

    @PostMapping("/address/edit")
    public String edit(Address address, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.Login_Member);

        memberService.addressEdit(memberId,address);

        return "redirect:/members/info";
    }
}
