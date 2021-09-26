package com.doydoit.springmvcjpa.domain.order;

import com.doydoit.springmvcjpa.domain.login.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/add")
    public String add(@RequestParam("itemId") Long itemId, @RequestParam("count") int count, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.Login_Member);

        orderService.createOrder(itemId, memberId, count);

        return "redirect:/items";
    }
}
