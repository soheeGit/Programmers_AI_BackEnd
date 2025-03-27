package org.example.chickenstock.controller;

import org.example.chickenstock.model.dto.Account;
import org.example.chickenstock.model.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    final AccountRepository accountRepository;

    public MainController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public String root(Model model) throws SQLException {
        List<Account> accouts = accountRepository.findAll();
        model.addAttribute("accounts", accouts);
        return "main";
    }

    @PostMapping
    public String save(@ModelAttribute Account account) throws Exception {
        accountRepository.save(account);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) throws Exception {
        accountRepository.delete(id);
        return "redirect:/";
    }
}