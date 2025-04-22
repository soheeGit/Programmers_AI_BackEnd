package org.example.bootjwt.controller;

import lombok.extern.java.Log;
import org.example.bootjwt.auth.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@Log
public class TestController {
    private final JwtTokenProvider jwtTokenProvider;

    public TestController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/")
    public ResponseEntity<Void> token(@RequestParam String token) {
        log.info(token);
        if (jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}