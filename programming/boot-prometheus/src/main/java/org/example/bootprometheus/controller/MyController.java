package org.example.bootprometheus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/ok")
    public ResponseEntity<Void> ok() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/not-ok")
    public ResponseEntity<Void> notOk() {
        return ResponseEntity.badRequest().build();
    }
}