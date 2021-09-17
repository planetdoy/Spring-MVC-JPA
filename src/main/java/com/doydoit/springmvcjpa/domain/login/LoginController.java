package com.doydoit.springmvcjpa.domain.login;

import com.doydoit.springmvcjpa.domain.member.Member;
import com.doydoit.springmvcjpa.web.member.MemberLoginForm;
import com.doydoit.springmvcjpa.web.member.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("/login")
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("member", new MemberSaveForm());
        return "login/loginForm";
    }

    @PostMapping
    public String login(@Validated @ModelAttribute("member") MemberLoginForm form, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        log.info("form = {}", form);

        // 로그인 서비스
        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if (member == null) {
            bindingResult.reject("error.notMatched", "아이디 혹은 비밀번호가 틀립니다");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.Login_Member,member.getId());
        session.setMaxInactiveInterval(600);// 10분

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }
}
