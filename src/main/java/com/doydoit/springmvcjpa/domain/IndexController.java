package com.doydoit.springmvcjpa.domain;

import com.doydoit.springmvcjpa.domain.login.SessionConst;
import com.doydoit.springmvcjpa.domain.member.Member;
import com.doydoit.springmvcjpa.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberRepository memberRepository;

    //@GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/")
    public String loginHome(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "index";
        }

        Long memberId = (Long) session.getAttribute(SessionConst.Login_Member);

        if (memberId == null) {
            return "index";
        }

        Member member = memberRepository.findById(memberId);

        model.addAttribute("member", member);
        return "login/loginHome";
    }
}

