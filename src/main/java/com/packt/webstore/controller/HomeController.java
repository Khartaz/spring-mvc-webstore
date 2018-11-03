package com.packt.webstore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/welcome")
    public String welcome(Model model) {
        model.addAttribute("greeting", "Welcome in online store");
        model.addAttribute("tagline", "The best one webstore");

        return "welcome";
    }
}