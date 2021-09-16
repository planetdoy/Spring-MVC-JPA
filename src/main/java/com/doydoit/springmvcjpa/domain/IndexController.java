package com.doydoit.springmvcjpa.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //@GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/")
    public String loginHome() {
        return "login/loginHome";
    }
}
