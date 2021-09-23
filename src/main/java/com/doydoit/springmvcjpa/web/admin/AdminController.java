package com.doydoit.springmvcjpa.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping
    public String home() {
        return "/admin/home";
    }

    @GetMapping("/items")
    public String itemList() {
        return "/admin/itemList";
    }

    @GetMapping("/orders")
    public String orderList() {
        return "/admin/orderList";
    }

}
