package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("greeting", "Welcome in online store");
        model.addAttribute("tagline", "The best one webstore");

        return "welcome";
    }
}