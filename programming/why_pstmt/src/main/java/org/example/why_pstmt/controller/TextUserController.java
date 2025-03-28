package org.example.why_pstmt.controller;

import org.example.why_pstmt.model.dao.TestUserRepository;
import org.example.why_pstmt.model.dto.TestUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class TextUserController {
    private final TestUserRepository testUserRepository;

    public TextUserController(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute TestUserDTO dto, Model model) throws SQLException {
        boolean result = testUserRepository.login(dto.username(), dto.password());
        model.addAttribute("message", result ? "로그인 성공 : %s님 환영합니다!".formatted(dto.username()) : "로그인 실패");
        return "index";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute TestUserDTO dto) throws SQLException {
        testUserRepository.createTestUser(dto.username(), dto.password());
        return "redirect:/";
    }
}
