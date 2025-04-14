package org.example.bootblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "김사원, 이따가 회의실로...");
        return "index";
    }
}
