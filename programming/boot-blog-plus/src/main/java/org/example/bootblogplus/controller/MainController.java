package org.example.bootblogplus.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("title", "행복한 세상의 수강생의 블로그");
        model.addAttribute("message", "행복한 수강생이란 수업을 마친 수강생 밖에 없어");
        model.addAttribute("user", principal);
        return "index";
    }
}